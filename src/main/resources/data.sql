-- Criação da tabela Users

CREATE TABLE IF NOT EXISTS Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    account_non_expired BOOLEAN DEFAULT TRUE,
    account_non_locked BOOLEAN DEFAULT TRUE,
    credentials_non_expired BOOLEAN DEFAULT TRUE,
    enabled BOOLEAN DEFAULT TRUE,
    full_name VARCHAR(255),
    password VARCHAR(255),
    user_name VARCHAR(255) UNIQUE NOT NULL
);

-- Criação da tabela Orders

CREATE TABLE IF NOT EXISTS Orders (
    orderId VARCHAR(255) PRIMARY KEY,
    applicantId INT NOT NULL,
    applicantName VARCHAR(255) NOT NULL,
    submissionDate DATE NOT NULL,
    licenseType INTEGER NOT NULL,
    status INTEGER NOT NULL,
    FOREIGN KEY (applicantId) REFERENCES Users(id)
);

-- Criação da tabela Documents

CREATE TABLE IF NOT EXISTS Documents (
    documentId VARCHAR(255) PRIMARY KEY,
    documentName VARCHAR(255) NOT NULL,
    documentType INTEGER NOT NULL,
    documentSize BIGINT NOT NULL,
    uploadDate DATE NOT NULL,
    orderId VARCHAR(255),
    fileUrl VARCHAR(255),
    FOREIGN KEY (orderId) REFERENCES Orders(orderId)
);

-- Criação da tabela Licenses

CREATE TABLE IF NOT EXISTS Licenses (
    licenseId VARCHAR(255) PRIMARY KEY,
    applicantId INT NOT NULL,
    orderId VARCHAR(255),
    licenseType INTEGER NOT NULL,
    issueDate DATE NOT NULL,
    FOREIGN KEY (orderId) REFERENCES Orders(orderId),
    FOREIGN KEY (applicantId) REFERENCES Users(id)
);

-- Criação da tabela Permissions

CREATE TABLE IF NOT EXISTS Permissions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    permission_name VARCHAR(255) NOT NULL
);

