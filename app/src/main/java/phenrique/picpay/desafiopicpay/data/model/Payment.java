package phenrique.picpay.desafiopicpay.data.model;

import com.google.gson.annotations.SerializedName;

public class Payment {

    @SerializedName("card_number")
    private String cardNumber;

    private int cvv;

    private float value;

    @SerializedName("expiry_date")
    private String expiryDate;

    @SerializedName("destination_user_id")
    private int destinationUserId;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getDestinationUserId() {
        return destinationUserId;
    }

    public void setDestinationUserId(int destinationUserId) {
        this.destinationUserId = destinationUserId;
    }
}
