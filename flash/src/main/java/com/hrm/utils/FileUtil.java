package com.hrm.utils;

import com.hrm.enums.FileType;

public class FileUtil {
	
	public static String ReturnFileExtension(FileType type)
    {
        switch (type)
        {
            case FileType.Pdf:
                return ".pdf";
            case FileType.Xls:
                return ".xls";
            case FileType.Csv:
                return ".csv";
            case FileType.Txt:
                return ".txt";
            case FileType.Doc:
                return ".doc";
            case FileType.Xlsx:
                return ".xlsx";
            case FileType.Docx:
                return ".docx";
            case FileType.Gif:
                return ".gif";
            case FileType.Jpg:
                return ".jpg";
            case FileType.Bmp:
                return ".bmp";
            case FileType.Png:
                return ".png";
            case FileType.Xml:
                return ".xml";
            case FileType.Html:
                return ".html";
            case FileType.Ppt:
                return ".ppt";
            case FileType.Pptx: 	
                return ".pptx";
            default:
                return "none";
        }
    }
	
	public static ICollection<FileInfo> GetFilesOfGivenType(string folder, FileType type)
    {
        return GetFilesOfGivenType(folder, type, string.Empty);
    }
	
	public static void DeleteFile(string name, string subFolder)
    {
        string fullPath = Path.Combine(subFolder, name);

        if (File.Exists(fullPath))
        {
            File.Delete(fullPath);
        }
    }
	
	public static string CopyFile(double waitTime, string oldName, string newName, string oldSubFolder, string newSubFolder)
    {
        Logger.Debug(CultureInfo.CurrentCulture, "new file name: {0}\\{1}", newSubFolder, newName);

        newName = NameHelper.ShortenFileName(newSubFolder, newName, "_", 255);

        if (File.Exists(newSubFolder + Separator + newName))
        {
            File.Delete(newSubFolder + Separator + newName);
        }

        Thread.Sleep(1000);
        File.Copy(oldSubFolder + Separator + oldName, newSubFolder + Separator + newName);

        var timeoutMessage = string.Format(CultureInfo.CurrentCulture, "Waiting till file will be copied {0}", newSubFolder);
        WaitHelper.Wait(() => File.Exists(newSubFolder + Separator + newName), TimeSpan.FromSeconds(waitTime), TimeSpan.FromSeconds(1), timeoutMessage);
        return newName;
    }
	
	public static string RenameFile(double waitTime, string oldName, string newName, string subFolder)
    {
        Logger.Debug(CultureInfo.CurrentCulture, "new file name: {0}", newName);

        newName = NameHelper.ShortenFileName(subFolder, newName, "_", 255);

        string fullPath = Path.Combine(subFolder, newName);

        if (File.Exists(fullPath))
        {
            File.Delete(fullPath);
        }

        CopyFile(waitTime, oldName, newName, subFolder);
        File.Delete(Path.Combine(subFolder, oldName));
        var timeoutMessage = string.Format(CultureInfo.CurrentCulture, "Waiting till file will be renamed {0}", subFolder);

        WaitHelper.Wait(() => File.Exists(subFolder + Separator + newName), TimeSpan.FromSeconds(waitTime), TimeSpan.FromSeconds(1), timeoutMessage);
        return newName;
    }
	
	public static string GetFolder(string appConfigValue, string currentFolder)
    {
        Logger.Trace(CultureInfo.CurrentCulture, "appConfigValue '{0}', currentFolder '{1}', UseCurrentDirectory '{2}'", appConfigValue, currentFolder, BaseConfiguration.UseCurrentDirectory);
        string folder;

        if (string.IsNullOrEmpty(appConfigValue))
        {
            folder = currentFolder;
        }
        else
        {
            if (BaseConfiguration.UseCurrentDirectory)
            {
                Regex pattern = new Regex("[\\]|[\\\\]|[/]|[//]");
                appConfigValue = pattern.Replace(appConfigValue, string.Empty);
                folder = currentFolder + FilesHelper.Separator + appConfigValue;
            }
            else
            {
                folder = appConfigValue;
            }

            if (!Directory.Exists(folder))
            {
                Directory.CreateDirectory(folder);
            }
        }

        Logger.Trace(CultureInfo.CurrentCulture, "Returned folder '{0}'", folder);
        return folder;
    }
}

}
