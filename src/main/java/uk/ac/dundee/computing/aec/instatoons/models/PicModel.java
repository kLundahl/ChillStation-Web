package uk.ac.dundee.computing.aec.instatoons.models;

/*
 * Expects a cassandra columnfamily defined as
 * use keyspace2;
 CREATE TABLE Tweets (
 user varchar,
 interaction_time timeuuid,
 tweet varchar,
 PRIMARY KEY (user,interaction_time)
 ) WITH CLUSTERING ORDER BY (interaction_time DESC);
 * To manually generate a UUID use:
 * http://www.famkruithof.net/uuid/uuidgen
 */
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.Bytes;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import static org.imgscalr.Scalr.*;
import org.imgscalr.Scalr.Method;

import uk.ac.dundee.computing.aec.instatoons.lib.*;
import uk.ac.dundee.computing.aec.instatoons.stores.Pic;
//import uk.ac.dundee.computing.aec.stores.TweetStore;

public class PicModel {

    Cluster cluster;
    
    public void PicModel() {

    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public void insertPic(byte[] b, String type, String name, String user, String filterSelection) {
        try {
            Convertors convertor = new Convertors();
            //Converts picture to string(?!)
            String types[]=Convertors.SplitFiletype(type);
            ByteBuffer buffer = ByteBuffer.wrap(b);
            int length = b.length;
            java.util.UUID picid = convertor.getTimeUUID();
            
            //The following is a quick and dirty way of doing this, will fill the disk quickly !
            Boolean success = (new File("/var/tmp/ChillStation/")).mkdirs();
            FileOutputStream output = new FileOutputStream(new File("/var/tmp/ChillStation/" + picid));

            output.write(b);
            byte []  thumbb = picresize(picid.toString(),types[1],filterSelection);
            int thumblength= thumbb.length;
            ByteBuffer thumbbuf=ByteBuffer.wrap(thumbb);
            byte[] processedb = picdecolour(picid.toString(),types[1], filterSelection);
            ByteBuffer processedbuf=ByteBuffer.wrap(processedb);
            int processedlength=processedb.length;
            Session session = cluster.connect("instatoons");
            //It connects to the db (cassandra) and then it inserts all the data of the submitted picture
            PreparedStatement psInsertPic = session.prepare("insert into pics ( picid, image,thumb,processed, user, interaction_time,imagelength,thumblength,processedlength,type,name) values(?,?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement psInsertPicToUser = session.prepare("insert into userpiclist ( picid, user, pic_added) values(?,?,?)");
            BoundStatement bsInsertPic = new BoundStatement(psInsertPic);
            BoundStatement bsInsertPicToUser = new BoundStatement(psInsertPicToUser);

            Date DateAdded = new Date();
            session.execute(bsInsertPic.bind(picid, buffer, thumbbuf,processedbuf, user, DateAdded, length,thumblength,processedlength, type, name));
            session.execute(bsInsertPicToUser.bind(picid, user, DateAdded));
            session.close();

        } catch (IOException ex) {
            System.out.println("Error --> " + ex);
        }
    }

    public byte[] picresize(String picid,String type,String filterSelection) {
        try {
            BufferedImage BI = ImageIO.read(new File("/var/tmp/ChillStation/" + picid));
            BufferedImage thumbnail = createThumbnail(BI,filterSelection);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(thumbnail, type, baos);
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException et) {

        }
        return null;
    }
    

    public byte[] picdecolour(String picid,String type, String filterSelection) {
        try {
            BufferedImage BI = ImageIO.read(new File("/var/tmp/ChillStation/" + picid));
            BufferedImage processed = createProcessed(BI,filterSelection);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(processed, type, baos);
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException et) {

        }
        return null;
    }

    public static BufferedImage createThumbnail(BufferedImage img,String filterSelection) {
        BufferedImage toon;
        //Checks if the filter selection is checked
    	if (filterSelection==null)
    		img = resize(img, Method.SPEED, 250, OP_ANTIALIAS);
    	else
    		img = resize(img, Method.SPEED, 250, OP_ANTIALIAS, OP_GRAYSCALE);
        // Let's add a little border before we return result.
    	toon=runToon(img);
        return pad(toon, 2);
    }
    
   public static BufferedImage createProcessed(BufferedImage img,String filterSelection) {
	    BufferedImage toon;
        int Width=img.getWidth()-1;
        //Checks if the filter selection is checked
        if (filterSelection==null)
        img = resize(img, Method.SPEED, Width, OP_ANTIALIAS);
        else
        img = resize(img, Method.SPEED, Width, OP_ANTIALIAS, OP_GRAYSCALE);
        toon=runToon(img);
        return pad(toon, 4);
    }
   
   //It is used to convert the picture to cartoon (actually, to oil painting)
	private static BufferedImage runToon(BufferedImage sourceImage)
		{

		int radius=10;
		int intensityLevels=24;

		BufferedImage dest=new BufferedImage(
		sourceImage.getWidth(),
		sourceImage.getHeight(),
		sourceImage.getType()
		);
		int averageR[] = new int[intensityLevels];
		int averageG[]=new int[intensityLevels];
		int averageB[]=new int[intensityLevels];
		int intensityCount[]=new int[intensityLevels];
		for(int x=0;x< sourceImage.getWidth();++x)
		{
			int left = Math.max(0,x-radius);
			int right = Math.min(x+radius,dest.getWidth()-1);
			for(int y=0;y< sourceImage.getHeight();++y)
			{
				int top = Math.max(0,y-radius);
				int bottom = Math.min(y+radius,dest.getHeight()-1);
				Arrays.fill(averageR,0);
				Arrays.fill(averageG,0);
				Arrays.fill(averageB,0);
				Arrays.fill(intensityCount,0);
				int maxIndex=-1;
			for(int j=top;j<=bottom;++j)
			{
				for(int i=left;i<=right;++i)
				{
				if(!inRange(x,y,i, j)) continue;
				int rgb = sourceImage.getRGB(i,j);
				int red = (rgb >> 16)&0xFF;
				int green = (rgb >>8)&0xFF;
				int blue = (rgb )&0xFF;
				int intensityIndex = (int)((((red+green+blue)/3.0)/256.0)*intensityLevels);
				intensityCount[intensityIndex]++;
				averageR[intensityIndex] += red;
				averageG[intensityIndex] += green;
				averageB[intensityIndex] += blue;
				if( maxIndex==-1 ||
						intensityCount[maxIndex]< intensityCount[intensityIndex]
				)
						{
							maxIndex = intensityIndex;
						}
				}
			}
				int curMax = intensityCount[maxIndex];
				int r = averageR[maxIndex] / curMax;
				int g = averageG[maxIndex] / curMax;
				int b = averageB[maxIndex] / curMax;
				int rgb=((r << 16) | ((g << 8) | b));
				dest.setRGB(x,y,rgb);
			}
		}
			return dest;
		}  
	
	private static boolean inRange(int cx,int cy,int i,int j)
	{
		double d;
		int radius=10;
		d=Point2D.distance(i, j,cx,cy);
		return d<radius;
	}
   
    public java.util.LinkedList<Pic> getPicsForUser(String User) {
        java.util.LinkedList<Pic> Pics = new java.util.LinkedList<>();
        Session session = cluster.connect("instatoons");
        PreparedStatement ps = session.prepare("select picid from userpiclist where user =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        User));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return null;
        } else {
            for (Row row : rs) {
                Pic pic = new Pic();
                java.util.UUID UUID = row.getUUID("picid");
                System.out.println("UUID" + UUID.toString());
                pic.setUUID(UUID);
                Pics.add(pic);

            }
        }
        return Pics;
    }

    public Pic getPic(int image_type, java.util.UUID picid) {
        Session session = cluster.connect("instatoons");
        ByteBuffer bImage = null;
        String type = null;
        int length = 0;
        try {
            Convertors convertor = new Convertors();
            ResultSet rs = null;
            PreparedStatement ps = null;
         
            if (image_type == Convertors.DISPLAY_IMAGE) {
                
                ps = session.prepare("select image,imagelength,type from pics where picid =?");
            } else if (image_type == Convertors.DISPLAY_THUMB) {
                ps = session.prepare("select thumb,imagelength,thumblength,type from pics where picid =?");
            } else if (image_type == Convertors.DISPLAY_PROCESSED) {
                ps = session.prepare("select processed,processedlength,type from pics where picid =?");
            }
            BoundStatement boundStatement = new BoundStatement(ps);
            rs = session.execute( // this is where the query is executed
                    boundStatement.bind( // here you are binding the 'boundStatement'
                            picid));

            if (rs.isExhausted()) {
                System.out.println("No Images returned");
                return null;
            } else {
                for (Row row : rs) {
                    if (image_type == Convertors.DISPLAY_IMAGE) {
                        bImage = row.getBytes("image");
                        length = row.getInt("imagelength");
                    } else if (image_type == Convertors.DISPLAY_THUMB) {
                        bImage = row.getBytes("thumb");
                        length = row.getInt("thumblength");
                
                    } else if (image_type == Convertors.DISPLAY_PROCESSED) {
                        bImage = row.getBytes("processed");
                        length = row.getInt("processedlength");
                    }
                    
                    type = row.getString("type");

                }
            }
        } catch (Exception et) {
            System.out.println("Can't get Pic" + et);
            return null;
        }
        session.close();
        Pic p = new Pic();
        p.setPic(bImage, length, type);

        return p;

    }

}
