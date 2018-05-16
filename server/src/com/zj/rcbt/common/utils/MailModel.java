package com.zj.rcbt.common.utils;

import java.util.Map;

public class MailModel {

        private String emailHost;
        private String emailFrom;
        private String emailUserName;
        private String emailPassword;
        private String toEmails;
        private String subject;
        private String content;
        private Map<String, String> pictures;
        private Map<String, String> attachments;
        private String fromAddress;
        private String toAddresses;
        private String[] attachFileNames;

        public MailModel() {
        }

        public String getFromAddress() {
            return this.fromAddress;
        }

        public void setFromAddress(String fromAddress) {
            this.fromAddress = fromAddress;
        }

        public String getToAddresses() {
            return this.toAddresses;
        }

        public void setToAddresses(String toAddresses) {
            this.toAddresses = toAddresses;
        }

        public String getSubject() {
            return this.subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String[] getAttachFileNames() {
            return this.attachFileNames;
        }

        public void setAttachFileNames(String[] attachFileNames) {
            this.attachFileNames = attachFileNames;
        }

        public String getEmailHost() {
            return this.emailHost;
        }

        public void setEmailHost(String emailHost) {
            this.emailHost = emailHost;
        }

        public String getEmailFrom() {
            return this.emailFrom;
        }

        public void setEmailFrom(String emailFrom) {
            this.emailFrom = emailFrom;
        }

        public String getEmailUserName() {
            return this.emailUserName;
        }

        public void setEmailUserName(String emailUserName) {
            this.emailUserName = emailUserName;
        }

        public String getEmailPassword() {
            return this.emailPassword;
        }

        public void setEmailPassword(String emailPassword) {
            this.emailPassword = emailPassword;
        }

        public String getToEmails() {
            return this.toEmails;
        }

        public void setToEmails(String toEmails) {
            this.toEmails = toEmails;
        }

        public Map<String, String> getPictures() {
            return this.pictures;
        }

        public void setPictures(Map<String, String> pictures) {
            this.pictures = pictures;
        }

        public Map<String, String> getAttachments() {
            return this.attachments;
        }

        public void setAttachments(Map<String, String> attachments) {
            this.attachments = attachments;
        }


}
