from BaseHTTPServer import BaseHTTPRequestHandler, HTTPServer
import BaseHTTPServer
import threading
import sys
import time
import json
from dbcommunicator import DBcommunicator


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

		request_type = request[1]
		comm = DBcommunicator()
		if request_type == 'add_user':
			res = comm.add_user(request)
			if 'ERROR' in res:
				# If arguments are too few, send a response with error
				self.send_response(400)
			else:
				self.send_response(200)
			self.send_header('Content-Type', 'text/html')
			self.end_headers()
			self.wfile.write(res)

		if request_type == 'get_users':
			res = comm.get_users()
			if 'ERROR' in res:
				self.send_response(400)
				self.send_header('Content-Type', 'text/html')
				self.end_headers()
				self.wfile.write(res)
				return
			self.send_response(200)
			self.send_header('Content-Type', 'text/html')
			self.end_headers()
			self.wfile.write(json.dumps({"users":res}))

		if request_type == 'login_user':
			res = comm.login_user(request)
			if 'ERROR' in res:
				self.send_response(400)
				self.send_header('Content-Type', 'text/html')
				self.end_headers()
				self.wfile.write(res)
				return
			self.send_response(200)
			self.send_header('Content-Type', 'text/html')
			self.end_headers()
			self.wfile.write(json.dumps(res))

		if request_type == 'get_transfers':
			res = comm.get_transfers(request)
			if 'ERROR' in res:
				self.send_response(400)
				self.send_header('Content-Type', 'text/html')
				self.end_headers()
				self.wfile.write(res)
				return
			self.send_response(200)
			self.send_header('Content-Type', 'text/html')
			self.end_headers()
			self.wfile.write(json.dumps({"transfers":res}))



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