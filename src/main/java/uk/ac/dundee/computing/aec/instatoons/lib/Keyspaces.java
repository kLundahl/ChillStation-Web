package uk.ac.dundee.computing.aec.instatoons.lib;

import com.datastax.driver.core.*;

public final class Keyspaces {

    public Keyspaces() {

    }

    public static void SetUpKeySpaces(Cluster c) {
        try {
            //Add some keyspaces here
            String createkeyspace = "create keyspace if not exists ChillStation WITH replication = {'class':'SimpleStrategy', 'replication_factor':1}";
            String CreateUser = "CREATE TABLE if not exists ChillStation.User (\n"
                    + "      login text PRIMARY KEY,\n"
                    + "      password text,\n"
                    + "  );";
            String CreatePlaylist = "CREATE TABLE if not exists ChillStation.Playlist (\n"
                    + "      user varchar,"
                    + "      playlistName varchar,"
                    + "      y_ID int,"
                    + "      s_ID int,"
                    + "      l_ID int,"
                    + " PRIMARY KEY (playlistName)"
                    + "  );";
            String CreateYoutube = "CREATE TABLE if not exists ChillStation.Youtube (\n"
                    + "      y_URL varchar,"
                    + "      y_length int,"
                    + "      y_name varchar,"
                    + "      y_ID int,"
                    + " PRIMARY KEY (y_URL)"
                    + "  );";
            String CreateSoundcloud = "CREATE TABLE if not exists ChillStation.Soundcloud (\n"
                    + "      s_URL varchar,"
                    + "      s_length int,"
                    + "      s_name varchar,"
                    + "      s_ID int,"
                    + " PRIMARY KEY (s_URL)"
                    + "  );";
            String CreateLocal = "CREATE TABLE if not exists ChillStation.Local (\n"
                    + "      l_URL varchar,"
                    + "      l_length int,"
                    + "      l_name varchar,"
                    + "      l_ID int,"
                    + " PRIMARY KEY (l_URL)"
                    + "  );";
            Session session = c.connect();
            try {
                PreparedStatement statement = session
                        .prepare(createkeyspace);
                BoundStatement boundStatement = new BoundStatement(
                        statement);
                ResultSet rs = session
                        .execute(boundStatement);
                System.out.println("created app ");
            } catch (Exception et) {
                System.out.println("Can't create app " + et);
            }

            //now add some column families 
            System.out.println("" + CreateUser);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateUser);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create user table" + et);
            }
            
            System.out.println("" + CreatePlaylist);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreatePlaylist);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create user table" + et);
            }
           
            
            System.out.println("" + CreateYoutube);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateYoutube);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create youtube table " + et);
            }
            
            
            System.out.println("" + CreateSoundcloud);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateSoundcloud);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create soundcloud table " + et);
            }
            
            
            System.out.println("" + CreateLocal);
            try {
                SimpleStatement cqlQuery = new SimpleStatement(CreateLocal);
                session.execute(cqlQuery);
            } catch (Exception et) {
                System.out.println("Can't create local  " + et);
            }
            session.close();

        } catch (Exception et) {
            System.out.println("Other keyspace or coulm definition error" + et);
        }

    }
}
