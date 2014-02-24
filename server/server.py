from BaseHTTPServer import BaseHTTPRequestHandler, HTTPServer
import BaseHTTPServer
import threading
import sys
import time


HOST_NAME = 'localhost'
PORT = 8888


#Hack to speed up
def _bare_address_string(self):
    host, port = self.client_address[:2]
    return '%s' % host
 
BaseHTTPServer.BaseHTTPRequestHandler.address_string = \
		_bare_address_string
#End hack


class RequestHandler(BaseHTTPRequestHandler):

	def do_GET(self):
		request = self.path.split('/')
		userIP = self.address_string()

		print request, userIP

def run_server(server):
	server.serve_forever()

if __name__ == '__main__':
	print "----Starting server..."
	server_address = (HOST_NAME, PORT)
	server = HTTPServer(server_address, RequestHandler)

	try:
		#Start HTTP server thread
		httpThread = threading.Thread(target = run_server, args = (server, ))
		httpThread.start()
		print "----HTTP Server started"

		# Keep alive
		while True:
			time.sleep(100)
		
	except (KeyboardInterrupt, SystemExit):
		print 
		print "----Server has shut down"
		server.shutdown()
		sys.exit(0)