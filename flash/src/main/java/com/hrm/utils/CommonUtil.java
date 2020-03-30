package com.hrm.utils;

public class CommonUtil {
	public static string RemoveSpecialCharacters(string name)
    {
        Logger.Debug(CultureInfo.CurrentCulture, "Removing all special characters except digit and letters from '{0}'", name);
        Regex rgx = new Regex("[^a-zA-Z0-9]");
        name = rgx.Replace(name, string.Empty);
        Logger.Debug(CultureInfo.CurrentCulture, "name without special characters: '{0}'", name);

        return name;
    }
	
	public static string RandomName(int length)
    {
        const string Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        var randomString = new StringBuilder();
        var random = new Random();

        for (int i = 0; i < length; i++)
        {
            randomString.Append(Chars[random.Next(Chars.Length)]);
        }

        return randomString.ToString();
    }
	
	public static string ShortenFileName(string folder, string fileName, string pattern, int maxLength)
    {
        Logger.Debug(CultureInfo.CurrentCulture, "Length of the file full name is {0} characters", (folder + fileName).Length);

        while (((folder + fileName).Length > maxLength) && fileName.Contains(pattern))
        {
            Logger.Trace(CultureInfo.CurrentCulture, "Length of the file full name is over {0} characters removing first occurrence of {1}", maxLength, pattern);
            Regex rgx = new Regex(pattern);
            fileName = rgx.Replace(fileName, string.Empty, 1);
            Logger.Trace(CultureInfo.CurrentCulture, "File full name: {0}", folder + fileName);
        }

        if ((folder + fileName).Length > 255)
        {
            Logger.Error(CultureInfo.CurrentCulture, "Length of the file full name is over {0} characters, try to shorten the name of tests", maxLength);
        }

        return fileName;
    }
	
	
	public void randomNumber() {
		
	}
}
