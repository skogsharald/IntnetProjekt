import psycopg2
import MySQLdb
import time

#TODO: Change host to mysql-vt2013.csc.kth.se:3306/ludjan
class DBcommunicator:

	def add_user(self, request):
		data = request[2].split('&')
		try:
			fname = data[0].split('=')[1]
			lname = data[1].split('=')[1]
			username = data[2].split('=')[1]
			password = data[3].split('=')[1]
			country = data[4].split('=')[1]
			email = data[5].split('=')[1]
		except IndexError:
			return 'ERROR: Too few arguments'
		connection = MySQLdb.connect(host='localhost', user='intnetuser', db='intnet', passwd='hejintnet')
		cursor = connection.cursor()
		cursor.execute('SELECT * FROM users WHERE username = %s', username)
		res1 = cursor.fetchone()
		cursor.execute('SELECT * FROM users WHERE email = %s', email)
		res2 = cursor.fetchone()
		if res1 is not None:
			message = 'ERROR: Username already in use'
		elif res2 is not None:
			message = 'ERROR: Email already in use'
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
		if len(users) == 0:
			message = 'ERROR: No users in database'
		else:
			message = users
		cursor.close()
		connection.close()
		return message

	def login_user(self, request):
		data = request[2].split('&')
		try:
			username = data[0].split('=')[1]
			password = data[1].split('=')[1]
		except IndexError:
			cursor.close()
			connection.close()
			return 'ERROR: Too few arguments'
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
		try:
			userID = int(data[0].split('=')[1])
		except IndexError:
			cursor.close()
			connection.close()
			return 'ERROR: Too few arguments'
		except TypeError:
			cursor.close()
			connection.close()
			return 'ERROR: userID is not an integer value'
		connection = MySQLdb.connect(host='localhost', user='intnetuser', db='intnet', passwd='hejintnet')
		cursor = connection.cursor(MySQLdb.cursors.DictCursor)
		cursor.execute('SELECT id, fromUser, toUser, amount, dt FROM transfers where fromUser = %s', userID)
		transfers = cursor.fetchall()
		cursor.close()
		connection.close()
		#Convert from datetime.datetime to date and time string
		for index in range(len(transfers)):
			transfers[index]['dt'] = transfers[index]['dt'].strftime("%Y-%m-%d %H:%M:%S")
		return transfers

	def get_user_currency(self, request):
		data = request[2].split('&')
		try:
			country = data[0].split('=')[1]
		except IndexError:
			cursor.close()
			connection.close()
			return 'ERROR: Too few arguments'
		connection = MySQLdb.connect(host='localhost', user='intnetuser', db='intnet', passwd='hejintnet')
		cursor = connection.cursor(MySQLdb.cursors.DictCursor)
		cursor.execute('SELECT currency FROM countries where countryName= %s', country)
		currency = cursor.fetchone()
		cursor.close()
		connection.close()
		return currency;

	def do_transfer(self, request): 
		data = request[2].split('&')
		try:
			fromUser = int(data[0].split('=')[1])
			toUser = int(data[1].split('=')[1])
			amount = float(data[2].split('=')[1])
			fromCurr = data[3].split('=')[1]
			transferType = data[4].split('=')[1]
		except IndexError:
			cursor.close()
			connection.close()
			return 'ERROR: Too few arguments'
		except ValueError:
			cursor.close()
			connection.close()
			return 'ERROR: only float and integer values are allowed'
		date = time.strftime('%Y-%m-%d %H:%M:%S')
		connection = MySQLdb.connect(host='localhost', user='intnetuser', db='intnet', passwd='hejintnet')
		cursor = connection.cursor(MySQLdb.cursors.DictCursor)
		try:
			cursor.execute('INSERT INTO transfers(fromUser, toUser, amount, fromCurr, type, dt) VALUES (%s,%s,%s,%s)', 
				(fromUser, toUser, amount, fromCurr, transferType, date))
			connection.commit()
		except:
			connection.rollback()
		cursor.close()
		connection.close()
		return 'Transfer complete'

	def update_user(self, request):
		data = request[2].split('&')
		try:
			userId = data[0].split('=')[1]
			fname = data[1].split('=')[1]
			lname = data[2].split('=')[1]
			username = data[3].split('=')[1]
			password = data[4].split('=')[1]
			country = data[5].split('=')[1]
			email = data[6].split('=')[1]
		except IndexError:
			cursor.close()
			connection.close()
			return 'ERROR: Too few arguments'
		connection = MySQLdb.connect(host='localhost', user='intnetuser', db='intnet', passwd='hejintnet')
		cursor = connection.cursor(MySQLdb.cursors.DictCursor)
		try:
			cursor.execute('UPDATE users SET fname = %s, lname= %s, username = %s, password = %s, country = %s, email = %s  WHERE id = %s ', 
				(fname, lname, username, password, country, email, userId))
			connection.commit()
		except:
			connection.rollback()
		cursor.close()
		connection.close()
		return 'User updated'




