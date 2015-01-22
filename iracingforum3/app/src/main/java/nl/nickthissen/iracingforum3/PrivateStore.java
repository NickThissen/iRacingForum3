package nl.nickthissen.iracingforum3;


import android.content.Context;
import android.text.TextUtils;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class PrivateStore
{
    private static final String CREDENTIALS_FILE = "credentials.txt";
    //private static final String FAVORITES_FILE = "favorites.json";
    private static final String DONATIONS_FILE = "donations.json";
    private static final String COOKIES_FILE = "cookies.txt";

    public static BasicCookieStore getStoredCookies(Context context, String username)
    {
        Log.info("Start loading cookies for " + username);

        try
        {
            File dir = context.getFilesDir();
            String pathname = dir.getAbsolutePath() + "/" + COOKIES_FILE;
            File file = new File(pathname);
            if (file.exists())
            {
                Log.info("> Cookie file found.");
                BasicCookieStore store = new BasicCookieStore();

                Scanner scanner = new Scanner(file);

                try
                {
                    // Get username
                    if (scanner.hasNextLine())
                    {
                        String uname = scanner.nextLine();
                        if (!uname.trim().toLowerCase().equals(username.trim().toLowerCase()))
                        {
                            Log.info("> Username did not match, deleting file.");
                            scanner.close();
                            file.delete();
                            return null;
                        }
                    }

                    while (scanner.hasNextLine())
                    {
                        String line = scanner.nextLine();
                        Log.info("> Start parsing cookie line: " + line);

                        String[] parts = line.split(";");
                        if (parts.length == 3)
                        {
                            String domain = parts[0];
                            String name = parts[1];
                            String value = parts[2];

                            BasicClientCookie c = new BasicClientCookie(name, value);
                            c.setDomain(domain);
                            store.addCookie(c);
                        }
                    }
                } finally
                {
                    scanner.close();
                }

                return store;
            }
        } catch (IOException ex)
        {
            return null;
        }
        return null;
    }

    public static void storeCookies(Context context, BasicCookieStore cookies, String username)
    {
        Log.info("Start saving cookies for " + username);

        try
        {
            FileOutputStream stream = context.openFileOutput(COOKIES_FILE, Context.MODE_PRIVATE);

            StringBuilder lines = new StringBuilder();
            lines.append(username + "\n");

            for (Cookie c : cookies.getCookies())
            {
                String domain = c.getDomain();
                String name = c.getName();
                String value = c.getValue();

                String line = domain + ";" + name + ";" + value;
                lines.append(line + "\n");

                Log.info("> Saving cookie line: " + line);
            }

            stream.write(lines.toString().getBytes());
            stream.close();

            Log.info("Cookies saved.");
        } catch (FileNotFoundException e)
        {
            Error error = Error.fromException("Error during credentials storing. Internal storage file not found.", e);
            error.log(context);
        } catch (IOException e)
        {
            Error error = Error.fromException("IO Error during credentials storing.", e);
            error.log(context);
        }
    }

    public static User getStoredLogin(Context context)
    {
        String username, password;

        try
        {
            FileInputStream stream = context.openFileInput(CREDENTIALS_FILE);
            DataInputStream inputStream = new DataInputStream(stream);

            String line1 = inputStream.readLine();
            if (line1 == null) return null;

            String line2 = inputStream.readLine();
            if (line2 == null) return null;

            inputStream.close();
            stream.close();

            username = line1.trim();
            password = line2.trim();

            User user = new User();
            user.username = username;
            user.password = password;
            return user;
        } catch (FileNotFoundException e)
        {
            return null;
        } catch (IOException e)
        {
            return null;
        }
    }

    public static void clearCookies(Context context)
    {
        File dir = context.getFilesDir();
        File file = new File(dir, COOKIES_FILE);
        if (file != null && file.exists())
        {
            file.delete();
        }
    }

    public static void clearLogin(Context context)
    {
        File dir = context.getFilesDir();
        File file = new File(dir, CREDENTIALS_FILE);
        if (file != null && file.exists())
        {
            file.delete();
        }
    }

    public static void storeLogin(Context context, User user)
    {
        try
        {
            FileOutputStream stream = context.openFileOutput(CREDENTIALS_FILE, Context.MODE_PRIVATE);
            String lines = user.username + "\n" + user.password;
            stream.write(lines.getBytes());
            stream.close();
        } catch (FileNotFoundException e)
        {
            Error error = Error.fromException("Error during credentials storing. Internal storage file not found.", e);
            error.report(context);
        } catch (IOException e)
        {
            Error error = Error.fromException("IO Error during credentials storing.", e);
            error.report(context);
        }
    }

    public static ArrayList<String> getDonations(Context context)
    {
        Gson g = new Gson();
        Type type = new TypeToken<ArrayList<String>>()
        {
        }.getType();

        String json = readFile(context, DONATIONS_FILE);
        if (TextUtils.isEmpty(json)) return new ArrayList<String>();

        ArrayList<String> names = g.fromJson(json, type);
        return names;
    }

    public static void saveDonations(Context context, ArrayList<String> names)
    {
        String json = "";
        if (names != null)
        {
            Gson g = new Gson();
            Type type = new TypeToken<ArrayList<String>>()
            {
            }.getType();
            json = g.toJson(names, type);
        }
        saveFile(context, DONATIONS_FILE, json);
    }

    private static String readFile(Context context, String filename)
    {
        try
        {
            File dir = context.getFilesDir();
            String pathname = dir.getAbsolutePath() + "/" + filename;
            File file = new File(pathname);
            if (file.exists())
            {
                StringBuilder fileContents = new StringBuilder((int) file.length());
                Scanner scanner = new Scanner(file);

                try
                {
                    while (scanner.hasNextLine())
                    {
                        fileContents.append(scanner.nextLine());
                    }
                    return fileContents.toString();
                } finally
                {
                    scanner.close();
                }
            }
        } catch (IOException ex)
        {
            Error.fromException("Error occurred while loading file.", ex).report(context);
        }
        return null;
    }

    private static void saveFile(Context context, String filename, String contents)
    {
        FileOutputStream stream;
        try
        {
            stream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            stream.write(contents.getBytes());
            stream.close();
        } catch (Exception ex)
        {
            Error.fromException("Error occurred while saving file.", ex).report(context);
        }
    }
}