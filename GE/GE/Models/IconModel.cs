using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace GE.Models
{
    class IconModel : AbstractImgModel
    {
        private int __x_position;
        private int __y_position;
        private Boolean __render;

        public IconModel(Image img, int x, int y, Boolean render) : base(img)
        {
            this.__x_position = x;
            this.__y_position = y;
            this.__render = render;
        }

        public override void render(Graphics canvas)
        {
            if (!__render) return;
            canvas.DrawImage(__img, __x_position, __y_position);
            canvas.Save();
        }
    }
}
