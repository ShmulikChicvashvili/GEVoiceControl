using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.Net.Sockets;

namespace GE.Communication
{
    class ServerSocket
    {
        const int BUFFER_SIZE = 10025;
        static void Main(string[] args)
        {
            IPHostEntry ipHost = Dns.GetHostEntry("");
            IPAddress ipAddr = ipHost.AddressList[0];
            TcpListener server = new TcpListener(IPAddress.Any, 30000);

            server.Start();
            Console.WriteLine(ipAddr);
            Console.WriteLine("Server Listening");

            TcpClient clientSocket = server.AcceptTcpClient();

            while (true)
            {
                //TcpClient clientSocket = default(TcpClient);
                Console.WriteLine("Got a Connection");

                NetworkStream networkStream = clientSocket.GetStream();
                byte[] bytesFrom = new byte[BUFFER_SIZE];
                networkStream.Read(bytesFrom, 0, BUFFER_SIZE);
                string dataFromClient = System.Text.Encoding.ASCII.GetString(bytesFrom);
                Console.WriteLine(dataFromClient);

                //string serverResponse = "Thank you!";
                //Byte[] sendBytes = Encoding.ASCII.GetBytes(serverResponse);
                //networkStream.Write(sendBytes, 0, BUFFER_SIZE);
                //networkStream.Flush();

            }

            clientSocket.Close();
            server.Stop();
        }
    }
}
