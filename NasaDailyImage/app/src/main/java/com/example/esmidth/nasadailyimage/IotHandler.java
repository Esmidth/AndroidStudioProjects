package com.example.esmidth.nasadailyimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.jar.Attributes;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class IotHandler extends DefaultHandler
{
    private String url = "http://www.nasa.gov/rss/image_of_the_day.rss";
    private boolean inUrl = false;
    private boolean inTitle =  false;
    private boolean inDescription = false;
    private boolean inItem = false;
    private boolean inDate = false;
    private Bitmap image  = null;
    private String title = null;
    private StringBuffer description = new StringBuffer();
    private String date = null;

    public void processFeed()
    {
        try
        {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(this);
            InputStream inputStream = new URL(url).openStream();
            reader.parse(new InputSource(inputStream));
        }catch (Exception e)
        {
        }
    }
    private Bitmap getBitmap(String url)
    {
        try{
            HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            input.close();
            return bitmap;
        }
        catch (IOException ioe)
        {
            return  null;
        }
    }
    public void startElement(String uri,String localName,String qName,Attributes attributes)
    {
        inUrl = localName.equals("url");

        if (localName.startsWith("item"))
        {
            inItem = true;
        }
        else  if(inItem)
        {
            inTitle = localName.equals("title");
            inDescription = localName.equals("description");
            inDate = localName.equals("pubDate");
        }

    }
    public void character(char ch[],int start,int length)
    {
        String chars = new String(ch).substring(start,start+length);
        if (inUrl && url == null)
            image = getBitmap(chars);
        if (inTitle && title == null)
            title = chars;
        if (inDescription)
            description.append(chars);
        if (inDate && date == null)
            date = chars;
    }

    public String getImage() {
        //return image;
        return "HelloWRold";
    }
    public String getTitle()
    {
        return title;
    }
    public StringBuffer getDescription()
    {
        return  description;
    }
    public String getDate()
    {
        return date;
    }


}
