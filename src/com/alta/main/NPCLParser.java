package com.alta.main;
import java.io.*;
import java.util.*;

public class NPCLParser
{

    public NPCLParser(File sourceFile)
    {
        commentChar = '#';
        isTokenizable(sourceFile);
    }

    public NPCLParser(File sourceFile, char commentChar)
    {
        this.commentChar = '#';
        this.commentChar = commentChar;
        isTokenizable(sourceFile);
    }

    public NPCLParser(String aString)
    {
        commentChar = '#';
        if(!isTokenizable(new File(aString)))
            tokenizer = new StreamTokenizer(new StringReader(aString));
    }

    public NPCLParser(String aString, char commentChar)
    {
        this.commentChar = '#';
        this.commentChar = commentChar;
        if(!isTokenizable(new File(aString)))
            tokenizer = new StreamTokenizer(new StringReader(aString));
    }

    public NPCLParser(StringBuffer strBuf)
    {
        commentChar = '#';
        tokenizer = new StreamTokenizer(new StringReader(strBuf.toString()));
    }

    public NPCLParser(StringBuffer strBuf, char commentChar)
    {
        this.commentChar = '#';
        this.commentChar = commentChar;
        tokenizer = new StreamTokenizer(new StringReader(strBuf.toString()));
    }

    public Hashtable getHashtable()
    {
        streamTokenInitializer();
        Object val = makeObjectCumExceptionCatcher();
        if(val instanceof Hashtable)
            return (Hashtable)val;
        else
            throw new RuntimeException("Invalid file");
    }

    public Vector getVector()
    {
        streamTokenInitializer();
        Object val = makeObjectCumExceptionCatcher();
        if(val instanceof Vector)
            return (Vector)val;
        else
            throw new RuntimeException("Invalid file");
    }

    private Object makeObjectCumExceptionCatcher()
    {
        try
        {
            Object obje = makeObject();
            if(fileReader != null)
                fileReader.close();
            sourceFile = null;
            return obje;
        }
        catch(IOException exc)
        {
            exc.printStackTrace();
        }
        throw new RuntimeException("Unexpected IOException thrown");
    }

    private Object makeObject()
        throws IOException
    {
        int c = tokenizer.nextToken();
        switch(c)
        {
        case -3: 
        case 34: // '"'
        case 39: // '\''
            return tokenizer.sval;

        case -2: 
            double d = tokenizer.nval;
            if(Math.floor(d) == d)
                return new Integer((int)d);
            else
                return new Double(d);

        case 123: // '{'
            tokenizer.pushBack();
            return makeDictionary();

        case 40: // '('
            tokenizer.pushBack();
            return makeVector();

        case 91: // '['
            tokenizer.nextToken();
            String dateTimeString = tokenizer.sval;
            StringTokenizer dateTimeTokenizer = new StringTokenizer(dateTimeString, "/:");
            if(tokenizer.nextToken() != 93)
                throw saParserException(tokenizer.lineno(), ']');
            if(dateTimeTokenizer.countTokens() == 6)
                return makeDate(dateTimeString);
            else
                return makeDateTime(dateTimeString);

        case 60: // '<'
            tokenizer.nextToken();
            String doubleValue = tokenizer.sval;
            if(tokenizer.nextToken() != 62)
                throw saParserException(tokenizer.lineno(), '>');
            else
                return new Double(doubleValue);
        }
        throw saParserException(tokenizer.lineno(), ' ');
    }

    private Hashtable makeDictionary()
        throws IOException
    {
        Hashtable hash = new Hashtable();
        int c = tokenizer.nextToken();
        if(c != 123)
            throw saParserException(tokenizer.lineno(), '{');
        Object key;
        Object value;
        for(; !endOfElement('}'); hash.put(key, value))
        {
            key = makeObject();
            if(tokenizer.nextToken() != 61)
                throw saParserException(tokenizer.lineno(), '=');
            value = makeObject();
            if(tokenizer.nextToken() != 59)
                throw saParserException(tokenizer.lineno(), ';');
            if(value.getClass().equals(java.lang.String.class) && ((String)value).toLowerCase().equals("no"))
                value = Boolean.FALSE;
            if(value.getClass().equals(java.lang.String.class) && ((String)value).toLowerCase().equals("yes"))
                value = Boolean.TRUE;
        }

        return hash;
    }

    private Vector makeVector()
        throws IOException
    {
        Vector vector = new Vector();
        int c = tokenizer.nextToken();
        if(c != 40)
            throw saParserException(tokenizer.lineno(), '(');
        do
        {
            if(endOfElement(')'))
                break;
            Object element = makeObject();
            vector.addElement(element);
            if(endOfElement(')'))
                break;
            if(tokenizer.nextToken() != 44)
                throw saParserException(tokenizer.lineno(), ',');
        } while(true);
        return vector;
    }

    private void streamTokenInitializer()
    {
        tokenizer.resetSyntax();
        tokenizer.whitespaceChars(0, 32);
        tokenizer.wordChars(33, 255);
        tokenizer.commentChar(commentChar);
        tokenizer.quoteChar(39);
        tokenizer.quoteChar(34);
        tokenizer.ordinaryChar(61);
        tokenizer.ordinaryChar(44);
        tokenizer.ordinaryChar(59);
        tokenizer.ordinaryChar(123);
        tokenizer.ordinaryChar(125);
        tokenizer.ordinaryChar(40);
        tokenizer.ordinaryChar(41);
        tokenizer.ordinaryChar(91);
        tokenizer.ordinaryChar(93);
        tokenizer.ordinaryChar(60);
        tokenizer.ordinaryChar(62);
        tokenizer.eolIsSignificant(false);
        tokenizer.parseNumbers();
    }

    private boolean endOfElement(char endChar)
        throws IOException
    {
        if(tokenizer.nextToken() == endChar)
        {
            return true;
        } else
        {
            tokenizer.pushBack();
            return false;
        }
    }

    private Date makeDate(String date)
    {
        return null;
    }

    private Date makeDateTime(String date)
    {
        return null;
    }

    private boolean isTokenizable(File sourceFile)
    {
        try
        {
            if(sourceFile.isFile())
            {
                fileReader = new FileReader(sourceFile);
                tokenizer = new StreamTokenizer(fileReader);
                this.sourceFile = sourceFile;
                return true;
            } else
            {
                return false;
            }
        }
        catch(FileNotFoundException exc)
        {
            throw new RuntimeException("File not found" + sourceFile);
        }
        catch(SecurityException e)
        {
            throw new RuntimeException("Have Security Exception");
        }
    }

    public void setCommentChar(char commentChar)
    {
        this.commentChar = commentChar;
    }

    private RuntimeException saParserException(int lineNo, char expectedToken)
    {
        return new RuntimeException("Unexpected token at line number: " + lineNo + " expecting " + expectedToken);
    }

    private StreamTokenizer tokenizer;
    private File sourceFile;
    private FileReader fileReader;
    private char commentChar;
}
