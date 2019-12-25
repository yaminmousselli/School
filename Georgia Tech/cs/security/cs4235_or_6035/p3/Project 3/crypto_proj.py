import hashlib
import json
import math
import os
import random
# You may NOT alter the import list!!!!
# The library, gmpy2, can be very helpful for this project --> @199

# Python Config: https://askubuntu.com/questions/320996/how-to-make-python-program-command-execute-python-3
# https://askubuntu.com/questions/815066/whats-the-difference-between-bashrc-and-etc-bash-bashrc

# Function Declaration and `self`: gcd(self, [rest of your parameters]) and then when you call the function try using self.gcd([rest of your parameters]) and see if that fixes it.


class CryptoProject(object):
    # self is the same as `this` in java. Use _init_ and self for OOP in python.
    # Use main for writing scripts
    def __init__(self):
        # TODO: Change this to YOUR Georgia Tech student ID!!!
        # Note that this is NOT your 9-digit Georgia Tech ID
        self.student_id = ''

    def get_student_id_hash(self):
        return hashlib.sha224(self.student_id.encode('UTF-8')).hexdigest()

    def get_all_data_from_json(self, filename):
        data = None
        base_dir = os.path.abspath(os.path.dirname(__file__))
        with open(os.path.join(base_dir, filename), 'r') as f:
            data = json.load(f)
        return data

    def get_data_from_json_for_student(self, filename):
        data = self.get_all_data_from_json(filename)
        name = self.get_student_id_hash()
        if name not in data:
            print(self.student_id + ' not in file ' + filename)
            return None
        else:
            return data[name]

    # TODO: OPTIONAL - Add helper functions below
    # BEGIN HELPER FUNCTIONS

    def modular_inverse_0(self, q, r):
        # _ means useless variable and you're not returning anything. You can name
        # it anything, but convention is _ for a variable (i.e., name in python) that isn't being used
        # You need it because we are returning three things.
        # return (g, x, y) such that a*x + b*y = g = gcd(a, b)
        g, s, _ = self.egcd_0(r, q)
        if g == 1:
            return s % q

    # Next time, try the recursive solution first.
    # I believe this is the most efficent implementation
    def modular_inverse_1(self, q, r):
        # don't need self if it's a method within a method
        def egcd_2(a, b):
            # recursive since egcd_0 takes forever. Third time writing this.
            # really should have done this in the first place
            if (a == 0):
                return (0, 1)
            else:
                c,d = egcd_2(b % a, a)
                return (d-(b//a)*c, c)

        d,c = egcd_2(q, r)
        return d % r

    def egcd_0(self, a, b):
    	x,y, u,v = 0,1, 1,0
    	while a != 0:
            # Remember that for any two numbers (either integer or float) in python x and y, x/y will ALWAYS be a float. x//y will be an integer. @246
    		q, r = b//a, b % a
    		m, n = x-u*q, y-v*q
    		b,a, x,y, u,v = a,r, u,v, m,n
    		gcd = b
    	return gcd, x, y

    def egcd_1(self, a, b):
        x,y, u,v = 0,1, 1,0
        while a != 0:
            q, r = b//a, b % a
            m, n = x-u*q, y-v*q
            b,a, x,y, u,v = a,r, u,v, m,n
            gcd = b
        return gcd

    def binary_search(self, num):
        '''
        We're gonna use binary search to compute the cube root which will lead us to
        the range to search in
        '''
        high = 1
        while high ** 3 <= num:
            high *= 2
        low = high//2

        while low < high:
            mid = (low + high)//2
            if (mid**3 > num):
                high = mid
            elif (mid**3 < num):
                low = mid
            else:
                return mid
        return mid + 1

    # END HELPER FUNCTIONS

    def decrypt_message(self, N, e, d, c):
        # TODO: Implement this function for Task 1
        # https://stackoverflow.com/questions/32738637/calculate-mod-using-pow-function-python
        # message = (c**d) % N # fucking inefficient
        message = pow(c, d, N)
        return hex(message).rstrip('L')

    def crack_password_hash(self, password_hash, weak_password_list):
        # TODO: Implement this function for Task 2
        drv_password_dne = 'PasswordDoesn\'tExistAndThisIsTheDefaultReturnValue'
        drv_salt_dne = 'SaltDoesn\'tExistAndThisIsTheDefaultReturnValue'
        for password in weak_password_list:
            for salt in weak_password_list:
                # ask a TA why we don't prepend and encode the salt first. It's considered good practice
                # to prepend the salt to the password before hashing. @214
                # hashed_password = hashlib.sha256(salt.encode() + password.encode()).hexdigest()
                hashed_password = hashlib.sha256(password.encode() + salt.encode()).hexdigest()
                if (hashed_password == password_hash):
                    return password, salt

        return drv_password_dne, drv_salt_dne

    def get_factors(self, n):
        # TODO: Implement this function for Task 3
        # @245
        for i in range(2,int(math.sqrt(n)+1), 3):
            if not(n % i):
                p = i
                # break;
        # print(p)
        q = n//p
        return p, q

    def get_private_key_from_p_q_e(self, p, q, e):
        # TODO: Implement this function for Task 3
        # Given an N, you can factor it into (p,q) and compute the private key prk
        # d = e^-1 mod totient(N) --> phi(N) = (p-1)*(q-1)
        # e^-1 is modular inverse of e mod phi(N). Extended Euclidean Algorithm!
        totient = (p-1)*(q-1)
        d = self.modular_inverse_0(totient, e)
        return d

    def is_waldo(self, n1, n2):
        # TODO: Implement this function for Task 4
        if(self.egcd_1(n1,n2) > 1):
            return True

        return False

    def get_private_key_from_n1_n2_e(self, n1, n2, e):
        # TODO: Implement this function for Task 4
        # Realized I could have used math.gcd(n1,n2) after the fact, but still had to do this
        p = self.egcd_1(n1,n2)
        q = n1//p
        totient = (p-1)*(q-1)
        d = self.modular_inverse_0(totient, e)
        return d

    def recover_msg(self, N1, N2, N3, C1, C2, C3):
        # TODO: Implement this function for Task 5
        # CRT
        # set up equations here
        a = (N2*N3)*self.modular_inverse_1(N2*N3, N1)*C1
        b = (N1*N3)*self.modular_inverse_1(N1*N3, N2)*C2
        c = (N1*N2)*self.modular_inverse_1(N1*N2, N3)*C3

        x = a+b+c
        y = x % (N1*N2*N3)

        # search for the cubic root
        m = self.binary_search(y)

        return m

    def task_1(self):
        data = self.get_data_from_json_for_student('keys4student_task_1.json')
        N = int(data['N'], 16)
        e = int(data['e'], 16)
        d = int(data['d'], 16)
        c = int(data['c'], 16)

        m = self.decrypt_message(N, e, d, c)
        return m

    def task_2(self):
        data = self.get_data_from_json_for_student('hashes4student_task_2.json')
        password_hash = data['password_hash']

        # The password file is loaded as a convenience
        weak_password_list = []
        base_dir = os.path.abspath(os.path.dirname(__file__))
        with open(os.path.join(base_dir, 'top_passwords.txt'), 'r', encoding='UTF-8-SIG') as f:
            pw = f.readline()
            while pw:
                weak_password_list.append(pw.strip('\n'))
                pw = f.readline()

        password, salt = self.crack_password_hash(password_hash, weak_password_list)

        return password, salt

    def task_3(self):
        data = self.get_data_from_json_for_student('keys4student_task_3.json')
        n = int(data['N'], 16)
        e = int(data['e'], 16)

        p, q = self.get_factors(n)
        d = self.get_private_key_from_p_q_e(p, q, e)

        return hex(d).rstrip('L')

    def task_4(self):
        all_data = self.get_all_data_from_json('keys4student_task_4.json')
        student_data = self.get_data_from_json_for_student('keys4student_task_4.json')
        n1 = int(student_data['N'], 16)
        e = int(student_data['e'], 16)

        d = 0
        waldo = 'Dolores'

        for classmate in all_data:
            if classmate == self.get_student_id_hash():
                continue
            n2 = int(all_data[classmate]['N'], 16)

            if self.is_waldo(n1, n2):
                waldo = classmate
                d = self.get_private_key_from_n1_n2_e(n1, n2, e)
                break

        return hex(d).rstrip("L"), waldo

    def task_5(self):
        data = self.get_data_from_json_for_student('keys4student_task_5.json')
        N1 = int(data['N0'], 16)
        N2 = int(data['N1'], 16)
        N3 = int(data['N2'], 16)
        C1 = int(data['C0'], 16)
        C2 = int(data['C1'], 16)
        C3 = int(data['C2'], 16)

        m = self.recover_msg(N1, N2, N3, C1, C2, C3)
        # Convert the int to a message string
        msg = bytes.fromhex(hex(m).rstrip('L')[2:]).decode('UTF-8')

        return msg
