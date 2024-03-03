import pymysql


def get_connection():
    conn = pymysql.connect(
        host='*****',
        user='*****',
        password="********",
        db='***********',
    )

    try:
        if conn.cursor():
            print("connection exists")
            return conn
        else:
            print("trying to reconnect")
    except Exception as e:
        return str(e)
    return conn


def execute_select_statement(sql_statement, only_one_row=True):
    conn = get_connection()
    cur = conn.cursor()
    cur.execute(sql_statement)
    if only_one_row:
        output = cur.fetchone()
    else:
        output = cur.fetchall()

    conn.close()
    return output


def execute_insert_statement(sql_statement):
    conn = get_connection()
    cur = conn.cursor()
    cur.execute(sql_statement)
    conn.commit()


if __name__ == "__main__":
    tester = get_connection().cursor()
    tester.execute("SELECT * FROM restaurants.restaurant_table")
    print(tester.fetchall())

