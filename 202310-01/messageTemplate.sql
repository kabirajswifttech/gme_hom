INSERT INTO public.message_templates
(message_tempate_id, template_type, purpose, "template", description, valid_from_date, valid_from_date_utc, valid_to_date, valid_to_date_utc, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, approved_by, approved_date, approved_date_utc, updated_by, updated_date, updated_date_utc, subject)
VALUES('d411167e-eb7d-43e7-9d37-8b6763dc6296'::uuid, 'EMAIL', 'SIGNUPEMAIL', '<html>
<head>
    <title>OTP Email</title>
    <style>       
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
        }
        .header {
            background-color: #007bff;
            color: #fff;
            text-align: center;
            padding: 10px;
        }
        .content {
            padding: 20px;
        }
        .button {
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            padding: 10px 20px;
            display: inline-block;
            border-radius: 5px;
        }
    </style>
</head>
<body>
   <div class="container">
        <div class="header">
            <h1>Your One-Time Password (OTP)</h1>
        </div>
        <div class="content">
           
    <p>Hello,</p>
    <p>Your OTP is: <strong>{{otp_code}}</strong></p>
    <p>This OTP is valid for a short period of time. Please use it to complete your action.</p>
    <p>If you did not request this OTP, please ignore this email.</p>
    <p>Thank you,</p>
    <p>
            Best regards,<br> <b>Global Money Express Co., Ltd. (GME)</b><br />
            |Glass Tower Building|Seoul|South Korea| <br />
            URL: www.gmeremit.com Ph no : 010-9839-6635
        </p>
        </div>
    </div>
</body>
</html>
', 'This template is used for sending email for one-time-otp during signup process through email.', NULL, NULL, NULL, NULL, NULL, 'active', true, NULL, 'system', '2023-09-21 14:19:31.116', '2023-09-21 08:34:31.116', NULL, NULL, NULL, NULL, NULL, NULL, 'Your One-Time Password (OTP)');
INSERT INTO public.message_templates
(message_tempate_id, template_type, purpose, "template", description, valid_from_date, valid_from_date_utc, valid_to_date, valid_to_date_utc, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, approved_by, approved_date, approved_date_utc, updated_by, updated_date, updated_date_utc, subject)
VALUES('0594b0c5-8d87-4f88-904c-463a3b88971d'::uuid, 'SMS', 'SIGNUPEMAIL', 'Your OTP is: {{otp_code}} . (GME)', 'This template is used for sending sms for one-time-otp during signup process to phone number.', NULL, NULL, NULL, NULL, NULL, 'active', true, NULL, 'system', '2023-09-21 14:26:35.142', '2023-09-21 08:41:35.142', NULL, NULL, NULL, NULL, NULL, NULL, 'Your One-Time Password (OTP)');
INSERT INTO public.message_templates
(message_tempate_id, template_type, purpose, "template", description, valid_from_date, valid_from_date_utc, valid_to_date, valid_to_date_utc, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, approved_by, approved_date, approved_date_utc, updated_by, updated_date, updated_date_utc, subject)
VALUES('7cd22593-6452-42e1-a0cf-3f1ff8cb1da9'::uuid, 'EMAIL', 'KYC_REJECTED', '<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KYC Details Rejected</title>
    <style>       
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
        }
        .header {
            background-color: #ff4c4c;
            color: #fff;
            text-align: center;
            padding: 10px;
        }
        .content {
            padding: 20px;
        }
        .button {
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            padding: 10px 20px;
            display: inline-block;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>KYC Details Rejected</h1>
        </div>
        <div class="content">
            <p>Hello {{full_name}},</p>
            <p>We regret to inform you that your KYC details have been rejected for the following reasons:</p>
            <ul>
               {{reason}}
            </ul>
            <p>We kindly request you to review and update your KYC information to address the mentioned issues. Once updated, please resubmit your KYC details for further processing.</p>
            <p>You can update your KYC information by clicking the following button:</p>
            <a class="button" href="{{link}}">Update KYC Details</a>
            <p>If you have any questions or need assistance, please donot hesitate to contact our support team at info@gmeremit.com.</p>
            <p>Thank you for your cooperation.</p>
               <p>
            Best regards,<br> <b>Global Money Express Co., Ltd. (GME)</b><br />
            |Glass Tower Building|Seoul|South Korea| <br />
            URL: www.gmeremit.com Ph no : 010-9839-6635
        </p>
        </div>
    </div>
</body>
</html>

', 'This template is used for sending email for KYC Details Rejected.', NULL, NULL, NULL, NULL, NULL, 'active', true, NULL, 'system', '2023-09-21 14:39:30.327', '2023-09-21 08:54:30.327', NULL, NULL, NULL, NULL, NULL, NULL, 'KYC Details Rejected');
INSERT INTO public.message_templates
(message_tempate_id, template_type, purpose, "template", description, valid_from_date, valid_from_date_utc, valid_to_date, valid_to_date_utc, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, approved_by, approved_date, approved_date_utc, updated_by, updated_date, updated_date_utc, subject)
VALUES('ad7ac13e-cedc-4bc4-bb70-479df295ca50'::uuid, 'EMAIL', 'ACCOUNT_CREATED', '<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Created Successfully</title>
	 <style>       
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
        }
        .header {
            background-color: #007bff;
            color: #fff;
            text-align: center;
            padding: 10px;
        }
        .content {
            padding: 20px;
        }
        .button {
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            padding: 10px 20px;
            display: inline-block;
            border-radius: 5px;
        }
    </style>
</head>
<body>
	  <div class="container">
        <div class="header">
            <h1>Welcome to Our Platform</h1>
        </div>
        <div class="content">
               <p>Dear {{full_name}},</p>
     <p>Your account has been created successfully.</p>
    <p>We have requested to reset your password.</p>
        <p>Please click the link below to reset your password:</p>
        <p><a href="{{Password_Reset_Link}}" style="background-color: #007bff; color: #fff; padding: 10px 20px; text-decoration: none; border-radius: 4px;">Reset Password</a></p>
        <p>If you didnot make this request, you can ignore this email.</p>
        <p>Thank you!</p>
    <p>
            Best regards,<br> <b>Global Money Express Co., Ltd. (GME)</b><br />
            |Glass Tower Building|Seoul|South Korea| <br />
            URL: www.gmeremit.com Ph no : 010-9839-6635
        </p>
        </div>
    </div>
	
	
</body>
</html>

', 'This template is used for sending email after account has been created successfully.', NULL, NULL, NULL, NULL, NULL, 'active', true, NULL, 'system', '2023-09-21 14:34:10.972', '2023-09-21 08:49:10.972', NULL, NULL, NULL, NULL, NULL, NULL, 'Welcome to Our Platform');
INSERT INTO public.message_templates
(message_tempate_id, template_type, purpose, "template", description, valid_from_date, valid_from_date_utc, valid_to_date, valid_to_date_utc, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, approved_by, approved_date, approved_date_utc, updated_by, updated_date, updated_date_utc, subject)
VALUES('a21aa43e-254c-4d72-8bc9-d82036ec4d88'::uuid, 'EMAIL', 'CREATE_PASSWORD', '<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Create Password</title>
	 <style>
        /* Inline CSS for styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
        }
        .header {
            background-color: #007bff;
            color: #fff;
            text-align: center;
            padding: 10px;
        }
        .content {
            padding: 20px;
        }
        .button {
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            padding: 10px 20px;
            display: inline-block;
            border-radius: 5px;
        }
    </style>
</head>
<body>		  <div class="container">
        <div class="header">
           <h1>Ceate Password</h1>
        </div>
        <div class="content">
               <p>Dear [Full Name],</p>
    
    <p>We have requested to create your password.</p>
        <p>Please click the link below to create your password:</p>
        <p><a href="[Link]" style="background-color: #007bff; color: #fff; padding: 10px 20px; text-decoration: none; border-radius: 4px;">Create Password</a></p>
		<p>If you have any questions or need further assistance, please don''t hesitate to contact us.</p>
   
        <p>If you didn''t make this request, you can ignore this email.</p>
        <p>Thank you!</p>
    <p>
            Best regards,<br> <b>Global Money Express Co., Ltd. (GME)</b><br />
            |Glass Tower Building|Seoul|South Korea| <br />
            URL: www.gmeremit.com Ph no : 010-9839-6635
        </p>
        </div>
    </div>
	
</body>
</html>
', 'This template is used for sending email for creating password when end user creates their respective users.', NULL, NULL, NULL, NULL, NULL, 'active', true, NULL, 'system', '2023-09-28 11:52:52.629', '2023-09-28 06:07:52.629', NULL, NULL, NULL, NULL, NULL, NULL, 'Create your password.');
INSERT INTO public.message_templates
(message_tempate_id, template_type, purpose, "template", description, valid_from_date, valid_from_date_utc, valid_to_date, valid_to_date_utc, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, approved_by, approved_date, approved_date_utc, updated_by, updated_date, updated_date_utc, subject)
VALUES('8c580929-e1f8-49bc-975c-9a7a3bd4554e'::uuid, 'EMAIL', 'RESET_PASSWORD', '<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Password Reset Link Sent</title>
	 <style>
        /* Inline CSS for styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
        }
        .header {
            background-color: #007bff;
            color: #fff;
            text-align: center;
            padding: 10px;
        }
        .content {
            padding: 20px;
        }
        .button {
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            padding: 10px 20px;
            display: inline-block;
            border-radius: 5px;
        }
    </style>
</head>
<body>		  <div class="container">
        <div class="header">
           <h1>Password Reset Link Sent</h1>
        </div>
        <div class="content">
               <p>Dear [Full Name],</p>
    
    <p>You have requested to reset your password.</p>
        <p>Please click the link below to reset your password:</p>
        <p><a href="{{Link}}" style="background-color: #007bff; color: #fff; padding: 10px 20px; text-decoration: none; border-radius: 4px;">Reset Password</a></p>
        <p>If you didn''t make this request, you can ignore this email.</p>
        <p>Thank you!</p>
    <p>
            Best regards,<br> <b>Global Money Express Co., Ltd. (GME)</b><br />
            |Glass Tower Building|Seoul|South Korea| <br />
            URL: www.gmeremit.com Ph no : 010-9839-6635
        </p>
        </div>
    </div>
	
</body>
</html>
', 'This template is used for sending email for one-time-otp during signup process through email.', NULL, NULL, NULL, NULL, NULL, 'active', true, NULL, 'system', '2023-09-28 12:29:27.999', '2023-09-28 06:44:27.999', NULL, NULL, NULL, NULL, NULL, NULL, 'Password Reset');
INSERT INTO public.message_templates
(message_tempate_id, template_type, purpose, "template", description, valid_from_date, valid_from_date_utc, valid_to_date, valid_to_date_utc, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, approved_by, approved_date, approved_date_utc, updated_by, updated_date, updated_date_utc, subject)
VALUES('01a7fd82-9658-4a23-b003-1be1aff3e7dd'::uuid, 'EMAIL', 'USER_LOCK', '<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Locked</title>
    <style>       
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
        }
        .header {
            background-color: #ff4c4c;
            color: #fff;
            text-align: center;
            padding: 10px;
        }
        .content {
            padding: 20px;
        }
        .button {
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            padding: 10px 20px;
            display: inline-block;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>User Locked</h1>
        </div>
        <div class="content">
            <p>Hello {{full_name}},</p>
            <p>We regret to inform you that  you have been locked for the following reasons:</p>
            <ul>
               {{reason}}
            </ul>
            <p>If you have any questions or need assistance, please don''t hesitate to contact our support team at info@gmeremit.com.</p>
            <p>Thank you for your cooperation.</p>
               <p>
            Best regards,<br> <b>Global Money Express Co., Ltd. (GME)</b><br />
            |Glass Tower Building|Seoul|South Korea| <br />
            URL: www.gmeremit.com Ph no : 010-9839-6635
        </p>
        </div>
    </div>
</body>
</html>

', 'This template is used for sending email if user is locked.', NULL, NULL, NULL, NULL, NULL, 'active', true, NULL, 'system', '2023-09-28 13:21:06.632', '2023-09-28 07:36:06.632', NULL, NULL, NULL, NULL, NULL, NULL, 'User Locked');
INSERT INTO public.message_templates
(message_tempate_id, template_type, purpose, "template", description, valid_from_date, valid_from_date_utc, valid_to_date, valid_to_date_utc, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, approved_by, approved_date, approved_date_utc, updated_by, updated_date, updated_date_utc, subject)
VALUES('d6bc5faa-b3b7-4633-ac22-e0a066cdda15'::uuid, 'EMAIL', 'USER_SUSPEND', '<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Suspended</title>
    <style>       
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
        }
        .header {
            background-color: #ff4c4c;
            color: #fff;
            text-align: center;
            padding: 10px;
        }
        .content {
            padding: 20px;
        }
        .button {
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            padding: 10px 20px;
            display: inline-block;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>User Suspended</h1>
        </div>
        <div class="content">
            <p>Hello {{full_name}},</p>
            <p>We regret to inform you that  you have been suspended for the following reasons:</p>
            <ul>
               {{reason}}
            </ul>
            <p>If you have any questions or need assistance, please don''t hesitate to contact our support team at info@gmeremit.com.</p>
            <p>Thank you for your cooperation.</p>
               <p>
            Best regards,<br> <b>Global Money Express Co., Ltd. (GME)</b><br />
            |Glass Tower Building|Seoul|South Korea| <br />
            URL: www.gmeremit.com Ph no : 010-9839-6635
        </p>
        </div>
    </div>
</body>
</html>


', 'This template is used for sending email if user is suspended.', NULL, NULL, NULL, NULL, NULL, 'active', true, NULL, 'system', '2023-09-28 13:22:05.732', '2023-09-28 07:37:05.732', NULL, NULL, NULL, NULL, NULL, NULL, 'User Suspended');
INSERT INTO public.message_templates
(message_tempate_id, template_type, purpose, "template", description, valid_from_date, valid_from_date_utc, valid_to_date, valid_to_date_utc, remarks, status, is_active, entity_hash, created_by, created_date, created_date_utc, approved_by, approved_date, approved_date_utc, updated_by, updated_date, updated_date_utc, subject)
VALUES('c1f5f3d0-8e05-48e7-98dd-44ad636a3068'::uuid, 'EMAIL', 'USER_ACTIVATED', '<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Activated</title>
      <style>
        /* Inline CSS for styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
        }
        .header {
            background-color: #007bff;
            color: #fff;
            text-align: center;
            padding: 10px;
        }
        .content {
            padding: 20px;
        }
        .button {
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            padding: 10px 20px;
            display: inline-block;
            border-radius: 5px;
        }
    </style>
</head>
<body>
 <div class="container">
        <div class="header">
            <h1>User Activated</h1>
        </div>
        <div class="content">
            <p>Hello {{full_name}},</p>
            <p>We are delighted to share with you that your access has been reinstated for the following reasons:</p>
            <ul>
               {{reason}}
            </ul>
            <p>If you have any questions or need assistance, please don''t hesitate to contact our support team at info@gmeremit.com.</p>
            <p>Thank you for your cooperation.</p>
               <p>
            Best regards,<br> <b>Global Money Express Co., Ltd. (GME)</b><br />
            |Glass Tower Building|Seoul|South Korea| <br />
            URL: www.gmeremit.com Ph no : 010-9839-6635
        </p>
        </div>
    </div>
</body>
</html>


', 'This template is used for sending email if user is activated.', NULL, NULL, NULL, NULL, NULL, 'active', true, NULL, 'system', '2023-09-28 13:23:21.581', '2023-09-28 07:38:21.581', NULL, NULL, NULL, NULL, NULL, NULL, 'User Activated');