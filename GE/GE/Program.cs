using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace GE
{
    class Program
    {
        const int WIDTH = 1024;
        const int HEIGHT = 1024;
        const string PATH = "C:\\Users\\Shmulik\\Programming\\Work related - Appr\\ProjectGE\\ProjectGEImpl\\GE\\GE\\Resources\\";

        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
            Image backgroundImg = Image.FromFile(PATH + "background.jpg");
            Image icon1 = Image.FromFile(PATH + "icon1.jpg");
            Image icon2 = Image.FromFile(PATH + "icon2.png");

            Bitmap myBitmap = new Bitmap(WIDTH, HEIGHT);
            Graphics canvas = Graphics.FromImage(myBitmap);
            canvas.InterpolationMode = System.Drawing.Drawing2D.InterpolationMode.HighQualityBicubic;
            canvas.DrawImage(backgroundImg,
                             new Rectangle(0,
                                           0,
                                           WIDTH,
                                           HEIGHT),
                             new Rectangle(0,
                                           0,
                                           backgroundImg.Width,
                                           backgroundImg.Height),
                             GraphicsUnit.Pixel);
            canvas.DrawImage(icon1,
                             (myBitmap.Width / 2) - (icon1.Width / 2),
                             (myBitmap.Height / 2) - (icon1.Height / 2));
            canvas.Save();

            myBitmap.Save(PATH + "newImage", System.Drawing.Imaging.ImageFormat.Jpeg);
        }
    }
}
