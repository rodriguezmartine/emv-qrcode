package com.emv.qrcode.model.cpm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.emv.qrcode.core.model.BERTLNumeric;
import com.emv.qrcode.core.model.BERTLString;
import com.emv.qrcode.core.model.BERTLV;
import com.emv.qrcode.core.model.BERTag;
import com.emv.qrcode.model.cpm.constants.TagTransactionProcessingCodes;

import lombok.Getter;

@Getter
public abstract class AdditionalData implements Serializable {

  private static final long serialVersionUID = -2275311356136693642L;

  private final Map<BERTag, BERTLV> additionalDataMap = new LinkedHashMap<>();

  public final void addAdditionalData(final BERTLV bertlv) {
    additionalDataMap.put(bertlv.getTag(), bertlv);
  }

  public final void setApplicationDefinitionFileName(final String applicationDefinitionFileName) {
    addAdditionalData(new BERTLString(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME, applicationDefinitionFileName));
  }

  public final void setApplicationLabel(final String applicationLabel) {
    addAdditionalData(new BERTLString(TagTransactionProcessingCodes.ID_APPLICATION_LABEL, applicationLabel));
  }

  public final void setTrack2EquivalentData(final String track2EquivalentData) {
    addAdditionalData(new BERTLString(TagTransactionProcessingCodes.ID_TRACK_2_EQUIVALENT_DATA, track2EquivalentData));
  }

  public final void setApplicationPAN(final String applicationPAN) {
    addAdditionalData(new BERTLNumeric(TagTransactionProcessingCodes.ID_APPLICATION_PAN, applicationPAN));
  }

  public final void setCardholderName(final String cardholderName) {
    addAdditionalData(new BERTLString(TagTransactionProcessingCodes.ID_CARDHOLDER_NAME, cardholderName));
  }

  public final void setLanguagePreference(final String languagePreference) {
    addAdditionalData(new BERTLString(TagTransactionProcessingCodes.ID_LANGUAGE_PREFERENCE, languagePreference));
  }

  public final void setIssuerURL(final String issuerURL) {
    addAdditionalData(new BERTLString(TagTransactionProcessingCodes.ID_ISSUER_URL, issuerURL));
  }

  public final void setApplicationVersionNumber(final String applicationVersionNumber) {
    addAdditionalData(new BERTLString(TagTransactionProcessingCodes.ID_APPLICATION_VERSION_NUMBER, applicationVersionNumber));
  }

  public final void setTokenRequestorID(final String tokenRequestorID) {
    addAdditionalData(new BERTLString(TagTransactionProcessingCodes.ID_TOKEN_REQUESTOR_ID, tokenRequestorID));
  }

  public final void setPaymentAccountReference(final String paymentAccountReference) {
    addAdditionalData(new BERTLString(TagTransactionProcessingCodes.ID_PAYMENT_ACCOUNT_REFERENCE, paymentAccountReference));
  }

  public final void setLast4DigitsOfPAN(final String last4DigitsOfPAN) {
    addAdditionalData(new BERTLString(TagTransactionProcessingCodes.ID_LAST_4_DIGITS_OF_PAN, last4DigitsOfPAN));
  }

  public final void setCryptogramInformationData(final String cryptogramInformationData) {
    addAdditionalData(new BERTLString(TagTransactionProcessingCodes.ID_CRYPTOGRAM_INFORMATION_DATA, cryptogramInformationData));
  }

  public final void setApplicationTransactionCounter(final String applicationTransactionCounter) {
    addAdditionalData(new BERTLString(TagTransactionProcessingCodes.ID_APPLICATION_TRANSACTION_COUNTER, applicationTransactionCounter));
  }

  public final void setApplicationCryptogram(final String applicationCryptogram) {
    addAdditionalData(new BERTLString(TagTransactionProcessingCodes.ID_APPLICATION_CRYPTOGRAM, applicationCryptogram));
  }

  public final void setIssuerApplicationData(final String issuerApplicationData) {
    addAdditionalData(new BERTLNumeric(TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA, issuerApplicationData));
  }

  public final BERTLV getAdditionalData(final BERTag tag) {
    return additionalDataMap.get(tag);
  }

  public final BERTLV getApplicationDefinitionFileName() {
    return additionalDataMap.get(TagTransactionProcessingCodes.ID_APPLICATION_DEFINITION_FILE_NAME);
  }

  public final BERTLV getApplicationLabel() {
    return additionalDataMap.get(TagTransactionProcessingCodes.ID_APPLICATION_LABEL);
  }

  public final BERTLV getTrack2EquivalentData() {
    return additionalDataMap.get(TagTransactionProcessingCodes.ID_TRACK_2_EQUIVALENT_DATA);
  }

  public final BERTLV getApplicationPAN() {
    return additionalDataMap.get(TagTransactionProcessingCodes.ID_APPLICATION_PAN);
  }

  public final BERTLV getCardholderName() {
    return additionalDataMap.get(TagTransactionProcessingCodes.ID_CARDHOLDER_NAME);
  }

  public final BERTLV getLanguagePreference() {
    return additionalDataMap.get(TagTransactionProcessingCodes.ID_LANGUAGE_PREFERENCE);
  }

  public final BERTLV getIssuerURL() {
    return additionalDataMap.get(TagTransactionProcessingCodes.ID_ISSUER_URL);
  }

  public final BERTLV getApplicationVersionNumber() {
    return additionalDataMap.get(TagTransactionProcessingCodes.ID_APPLICATION_VERSION_NUMBER);
  }

  public final BERTLV getTokenRequestorID() {
    return additionalDataMap.get(TagTransactionProcessingCodes.ID_TOKEN_REQUESTOR_ID);
  }

  public final BERTLV getPaymentAccountReference() {
    return additionalDataMap.get(TagTransactionProcessingCodes.ID_PAYMENT_ACCOUNT_REFERENCE);
  }

  public final BERTLV getLast4DigitsOfPAN() {
    return additionalDataMap.get(TagTransactionProcessingCodes.ID_LAST_4_DIGITS_OF_PAN);
  }

  public final BERTLV getCryptogramInformationData() {
    return additionalDataMap.get(TagTransactionProcessingCodes.ID_CRYPTOGRAM_INFORMATION_DATA);
  }

  public final BERTLV getApplicationTransactionCounter() {
    return additionalDataMap.get(TagTransactionProcessingCodes.ID_APPLICATION_TRANSACTION_COUNTER);
  }

  public final BERTLV getApplicationCryptogram() {
    return additionalDataMap.get(TagTransactionProcessingCodes.ID_APPLICATION_CRYPTOGRAM);
  }

  public final BERTLV getIssuerApplicationData() {
    return additionalDataMap.get(TagTransactionProcessingCodes.ID_ISSUER_APPLICATION_DATA);
  }

  public byte[] getBytes() throws IOException {
    try (final ByteArrayOutputStream out = new ByteArrayOutputStream()) {

      for (final Entry<BERTag, BERTLV> entry : additionalDataMap.entrySet()) {
        out.write(entry.getValue().getBytes());
      }

      return out.toByteArray();
    }
  }

}
