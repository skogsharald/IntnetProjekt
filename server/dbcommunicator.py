import psycopg2
import MySQLdb

class DBcommunicator:

	def add_user(self, request):
		data = request[2].split('&')
		if len(data) < 5:
			return 'ERROR: Too few arguments'
		fname = data[0].split('=')[1]
		lname = data[1].split('=')[1]
		username = data[2].split('=')[1]
		password = data[3].split('=')[1]
		country = data[4].split('=')[1]
		email = data[5].split('=')[1]
		connection = MySQLdb.connect(host='localhost', user='intnetuser', db='intnet', passwd='hejintnet')
		cursor = connection.cursor()
		cursor.execute('SELECT * FROM users WHERE username = %s', username)
		res = cursor.fetchone()
		if res is not None:
			message = 'ERROR: Username already in use'
		else:
			cursor.execute('INSERT INTO users(fname,lname,username,password,country,email) VALUES(%s, %s, %s, %s, %s, %s)', (fname, 
				lname, username, password, country, email))
			connection.commit()
			message = 'User inserted to database'
		cursor.close()
		connection.close()
		return message


	def get_users(self):
		connection = MySQLdb.connect(host='localhost', user='intnetuser', db='intnet', passwd='hejintnet')
		cursor = connection.cursor(MySQLdb.cursors.DictCursor)
		cursor.execute('SELECT id,fname,lname,username,password,country,email FROM users')
		users = cursor.fetchall()
		print len(users)
		if len(users) == 0:
			message = 'ERROR: No users in database'
		else:
			message = users
		cursor.close()
		connection.close()
		return message

	def login_user(self, request):
		data = request[2].split('&')
		if len(data) < 2:
			return 'ERROR: Too few arguments'
		username = data[0].split('=')[1]
		password = data[1].split('=')[1]
		connection = MySQLdb.connect(host='localhost', user='intnetuser', db='intnet', passwd='hejintnet')
		cursor = connection.cursor(MySQLdb.cursors.DictCursor)
		cursor.execute('SELECT * FROM users WHERE username = %s AND password = %s', (username, password))
		user = cursor.fetchone()
		if user is None:
			message = 'ERROR: Incorrect login'
		else:
			message = user
		cursor.close()
		connection.close()
		return message

	def get_transfers(self, request):
		data = request[2].split('&')
		print data
		if data[0] == '':
			return 'ERROR: Too few arguments'
		username = data[0].split('=')[1]
		connection = MySQLdb.connect(host='localhost', user='intnetuser', db='intnet', passwd='hejintnet')
		cursor = connection.cursor(MySQLdb.cursors.DictCursor)
		cursor.execute('SELECT * FROM transfers where fromUser = %s', username)
		transfers = cursor.fetchall()
		cursor.close()
		connection.close()
		return transfers



