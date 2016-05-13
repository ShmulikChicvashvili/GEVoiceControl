using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace GE.Models
{
    abstract class AbstractImgModel
    {
        protected Image __img;

        public AbstractImgModel(Image img)
        {
            __img = img;
        }

        public virtual void render(Graphics canvas)
        {
            // Nothing todo here, purely abstract
        }
    }
}
