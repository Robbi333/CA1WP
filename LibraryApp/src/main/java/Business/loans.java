package Business;

import java.io.Serializable;
import java.util.Date;

public class loans implements Serializable {
        private int loanID;
        private int memberID;
        private int bookID;
        private Date loanDate;
        private Date dueDate;
        private Date returnDate;
        private double lateFee;

        public loans(int loanID, int memberID, int bookID, Date loanDate, Date dueDate, Date returnDate, double lateFee) {
            this.loanID = loanID;
            this.memberID = memberID;
            this.bookID = bookID;
            this.loanDate = loanDate;
            this.dueDate = dueDate;
            this.returnDate = returnDate;
            this.lateFee = lateFee;
        }

        public loans() {

        }

        public int getLoanID() {
            return loanID;
        }

        public int getMemberID() {
            return memberID;
        }

        public int getBookID() {
            return bookID;
        }

        public Date getLoanDate() {
            return loanDate;
        }

        public Date getDueDate() {
            return dueDate;
        }

        public Date getReturnDate() {
            return returnDate;
        }

        public double getLateFee() {
            return lateFee;
        }

        public void setLoanID(int loanID) {
            this.loanID = loanID;
        }

        public void setMemberID(int memberID) {
            this.memberID = memberID;
        }

        public void setBookID(int bookID) {
            this.bookID = bookID;
        }

        public void setLoanDate(Date loanDate) {
            this.loanDate = loanDate;
        }

        public void setDueDate(Date dueDate) {
            this.dueDate = dueDate;
        }

        public void setReturnDate(Date returnDate) {
            this.returnDate = returnDate;
        }

        public void setLateFee(double lateFee) {
            this.lateFee = lateFee;
        }



        @Override
        public String toString() {
            return "Loan{" +
                    "loanID=" + loanID +
                    ", memberID=" + memberID +
                    ", bookID=" + bookID +
                    ", loanDate=" + loanDate +
                    ", dueDate=" + dueDate +
                    ", returnDate=" + returnDate +
                    ", lateFee=" + lateFee +
                    '}';
        }


    }
    
