using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.Net.Sockets;

namespace ClientSocketNETPOC
{
    class Program
    {
        static void Main(string[] args)
        {
            TcpClient sock = new TcpClient();
            sock.Connect("46.19.85.13", 30000);

            NetworkStream serverStream = sock.GetStream();
            byte[] outStream = System.Text.Encoding.ASCII.GetBytes("Hello World!!!");
            serverStream.Write(outStream, 0, outStream.Length);
            serverStream.Flush();
        }
    }
}
