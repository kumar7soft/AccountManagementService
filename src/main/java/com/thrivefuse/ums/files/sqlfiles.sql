use UR;

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    middle_name VARCHAR(255),
    primary_phone VARCHAR(255),
    secondary_phone VARCHAR(255),
    address TEXT,
    state VARCHAR(255),
    country VARCHAR(255),
    city VARCHAR(255),
    zip VARCHAR(255),
    pan_adhar_id BIGINT,
    user_credentials_id BIGINT, -- Adjusted column name to be more descriptive
    created_date DATETIME,
    is_verified BOOLEAN,
    is_active BOOLEAN,
    FOREIGN KEY (pan_adhar_id) REFERENCES user_pan_adhar_details(id),
    FOREIGN KEY (user_credentials_id) REFERENCES user_credentials(id)
);

CREATE TABLE user_credentials (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255)
);

CREATE TABLE user_pan_adhar_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    adhar_card_number VARCHAR(255),
    pan_card_number VARCHAR(255)
);


CREATE TABLE otp_verification (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(10) NOT NULL,
    identifier VARCHAR(255) NOT NULL,
    otp VARCHAR(10) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP NOT NULL,
    verified BOOLEAN DEFAULT FALSE
);
commit