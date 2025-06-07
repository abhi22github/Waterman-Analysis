import sys
import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart

def main():
    if len(sys.argv) != 10:
        print("Usage: emailprog.py <email> <username> <phone> <address> <date> <canName> <canCapacity> <mode> <amount>")
        sys.exit(1)

    demail = sys.argv[1]
    duname = sys.argv[2]
    dph = sys.argv[3]
    daddress = sys.argv[4]
    ddate = sys.argv[5]
    dcanName = sys.argv[6]
    dcanCapacity = sys.argv[7]
    dmode = sys.argv[8]
    damount = sys.argv[9]

    # Email account credentials used to send the email (replace these)
    sender_email = "youremail@example.com"
    sender_password = "yourpassword"
    smtp_server = "smtp.example.com"
    smtp_port = 587  # For TLS

    # Compose the email
    subject = "Your Water Can Order Confirmation"
    body = f"""
    Dear {duname},

    Thank you for your order. Here are your order details:

    Phone: {dph}
    Address: {daddress}
    Date: {ddate}
    Can Name: {dcanName}
    Can Capacity: {dcanCapacity}
    Payment Mode: {dmode}
    Amount: ₹{damount}

    Your order will be processed soon.

    Regards,
    WaterCopy Team
    """

    # Create MIME message
    message = MIMEMultipart()
    message["From"] = sender_email
    message["To"] = demail
    message["Subject"] = subject
    message.attach(MIMEText(body, "plain"))

    try:
        # Connect to SMTP server and send email
        server = smtplib.SMTP(smtp_server, smtp_port)
        server.starttls()  # Secure the connection
        server.login(sender_email, sender_password)
        server.sendmail(sender_email, demail, message.as_string())
        server.quit()
        print("Email sent successfully to", demail)
    except Exception as e:
        print("Failed to send email:", e)
        sys.exit(1)

if __name__ == "__main__":
    main()
