package com.hrm.utils;

public class ScreenShotUtil {
	 public static string TakeScreenShotOfElement(IWebElement element, string folder, string screenshotName)
     {
         Logger.Debug("Taking screenhot of element not within iframe");
         return TakeScreenShotOfElement(0, 0, element, folder, screenshotName);
     }
	 
	 public static string TakeScreenShotOfElement(int iframeLocationX, int iframeLocationY, IWebElement element, string folder, string screenshotName)
     {
         Logger.Debug("Taking screenhot of iframe LocationX:{0} LocationY:{1}", iframeLocationX, iframeLocationY);
         var locationX = iframeLocationX;
         var locationY = iframeLocationY;

         var driver = element.ToDriver();

         var screenshotDriver = (ITakesScreenshot)driver;
         var screenshot = screenshotDriver.GetScreenshot();
         var filePath = Path.Combine(folder, DateTime.Now.ToString("yyyy-MM-dd_HH-mm-ss-fff", CultureInfo.CurrentCulture) + "temporary_fullscreen.png");
         Logger.Debug(CultureInfo.CurrentCulture, "Taking full screenshot {0}", filePath);
         screenshot.SaveAsFile(filePath, ScreenshotImageFormat.Png);

         if (BaseConfiguration.TestBrowser == BrowserType.Chrome)
         {
             locationX = element.Location.X + locationX;
             locationY = element.Location.Y + locationY;
         }
         else
         {
             locationX = element.Location.X;
             locationY = element.Location.Y;
         }

         var elementWidth = element.Size.Width;
         var elementHeight = element.Size.Height;

         return CutOutScreenShot(folder, screenshotName, locationX, locationY, elementWidth, elementHeight, filePath);
     }

}
