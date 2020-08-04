package com.emv.qrcode.validators.mpm;

import org.junit.Test;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.model.mpm.AdditionalDataField;
import com.emv.qrcode.model.mpm.AdditionalDataFieldValue;
import com.emv.qrcode.model.mpm.MerchantAccountInformation;
import com.emv.qrcode.model.mpm.MerchantAccountInformationValue;
import com.emv.qrcode.model.mpm.MerchantInformationLanguage;
import com.emv.qrcode.model.mpm.MerchantInformationLanguageValue;
import com.emv.qrcode.model.mpm.MerchantPresentMode;
import com.emv.qrcode.model.mpm.Unreserved;
import com.emv.qrcode.model.mpm.UnreservedValue;

import br.com.fluentvalidator.context.ValidationResult;

public class MerchantPresentModeValidatorTest {

  private final MerchantPresentModeValidator validator = new MerchantPresentModeValidator();

  @Test
  public void testSuccessValidate() {

    final AdditionalDataField additionalDataField = getAddtionalDataField();
    final MerchantAccountInformation merchantAccountInformation = getMerchanAccountInformation();
    final MerchantInformationLanguage merchantInformationLanguage = getMerchantInformationLanguage();
    final Unreserved unreserved = getUnreserved();
    final TagLengthString countryCode = new TagLengthString("5802CN");
    final TagLengthString merchantCategoryCode = new TagLengthString("52044111");
    final TagLengthString merchantCity = new TagLengthString("6007BEIJING");
    final TagLengthString merchantName = new TagLengthString("5914BEST TRANSPORT");
    final TagLengthString payloadFormatIndicator = new TagLengthString("000201");
    final TagLengthString pointOfInitiationMethod = new TagLengthString("010211");
    final TagLengthString postalCode = new TagLengthString("61071234567");
    final TagLengthString tipOrConvenienceIndicator = new TagLengthString("550201");
    final TagLengthString transactionAmount = new TagLengthString("540523.72");
    final TagLengthString transactionCurrency = new TagLengthString("5303156");
    final TagLengthString valueOfConvenienceFeeFixed = new TagLengthString("5603500");
    final TagLengthString valueOfConvenienceFeePercentage = new TagLengthString("57015");
    final TagLengthString rFUforEMVCo = new TagLengthString("650200");

    final MerchantPresentMode merchantPresentMode = new MerchantPresentMode();
    merchantPresentMode.setAdditionalDataField(additionalDataField);
    merchantPresentMode.setCountryCode(countryCode);
    merchantPresentMode.setMerchantCategoryCode(merchantCategoryCode);
    merchantPresentMode.setMerchantCity(merchantCity);
    merchantPresentMode.setMerchantInformationLanguage(merchantInformationLanguage);
    merchantPresentMode.setMerchantName(merchantName);
    merchantPresentMode.setPayloadFormatIndicator(payloadFormatIndicator);
    merchantPresentMode.setPointOfInitiationMethod(pointOfInitiationMethod);
    merchantPresentMode.setPostalCode(postalCode);
    merchantPresentMode.setTipOrConvenienceIndicator(tipOrConvenienceIndicator);
    merchantPresentMode.setTransactionAmount(transactionAmount);
    merchantPresentMode.setTransactionCurrency(transactionCurrency);
    merchantPresentMode.setValueOfConvenienceFeeFixed(valueOfConvenienceFeeFixed);
    merchantPresentMode.setValueOfConvenienceFeePercentage(valueOfConvenienceFeePercentage);
    merchantPresentMode.addMerchantAccountInformation(merchantAccountInformation);
    merchantPresentMode.addRFUforEMVCo(rFUforEMVCo);
    merchantPresentMode.addUnreserved(unreserved);

    final ValidationResult validationResult = validator.validate(merchantPresentMode);
  }

  private MerchantAccountInformation getMerchanAccountInformation() {
    final TagLengthString globallyUniqueIdentifier = new TagLengthString();
    globallyUniqueIdentifier.setTag("00");
    globallyUniqueIdentifier.setValue("hoge");

    final TagLengthString paymentNetworkSpecific = new TagLengthString();
    paymentNetworkSpecific.setTag("01");
    paymentNetworkSpecific.setValue("abcd");

    final MerchantAccountInformationValue merchantAccountInformationValue = new MerchantAccountInformationValue();
    merchantAccountInformationValue.setGloballyUniqueIdentifier(globallyUniqueIdentifier);
    merchantAccountInformationValue.addPaymentNetworkSpecific(paymentNetworkSpecific);

    final MerchantAccountInformation merchantAccountInformation = new MerchantAccountInformation();
    merchantAccountInformation.setValue(merchantAccountInformationValue);
    merchantAccountInformation.setTag("02");
    return merchantAccountInformation;
  }

  private Unreserved getUnreserved() {
    final TagLengthString globallyUniqueIdentifier = new TagLengthString();
    globallyUniqueIdentifier.setTag("00");
    globallyUniqueIdentifier.setValue("A011223344998877");

    final TagLengthString contextSpecificData = new TagLengthString();
    contextSpecificData.setTag("07");
    contextSpecificData.setValue("12345678");

    final UnreservedValue value = new UnreservedValue();
    value.setGloballyUniqueIdentifier(globallyUniqueIdentifier);
    value.addContextSpecificData(contextSpecificData);

    final Unreserved unreserved = new Unreserved();
    unreserved.setValue(value);
    unreserved.setTag("80");
    return unreserved;
  }

  private MerchantInformationLanguage getMerchantInformationLanguage() {
    final TagLengthString languagePreference = new TagLengthString();
    languagePreference.setTag("00");
    languagePreference.setValue("ZH");

    final TagLengthString merchantName = new TagLengthString();
    merchantName.setTag("01");
    merchantName.setValue("北京");

    final TagLengthString merchantCity = new TagLengthString();
    merchantCity.setTag("02");
    merchantCity.setValue("最佳运输");

    final TagLengthString rFUforEMVCo = new TagLengthString();
    rFUforEMVCo.setTag("03");
    rFUforEMVCo.setValue("abcd");

    final MerchantInformationLanguageValue merchantInformationLanguageValue = new MerchantInformationLanguageValue();
    merchantInformationLanguageValue.setLanguagePreference(languagePreference);
    merchantInformationLanguageValue.setMerchantName(merchantName);
    merchantInformationLanguageValue.setMerchantCity(merchantCity);
    merchantInformationLanguageValue.addRFUforEMVCo(rFUforEMVCo);

    final MerchantInformationLanguage merchantInformationLanguage = new MerchantInformationLanguage();
    merchantInformationLanguage.setValue(merchantInformationLanguageValue);
    return merchantInformationLanguage;
  }

  private AdditionalDataField getAddtionalDataField() {
    final TagLengthString additionalConsumerDataRequest = new TagLengthString();
    additionalConsumerDataRequest.setTag("09");
    additionalConsumerDataRequest.setValue("tuvxy");

    final TagLengthString billNumber = new TagLengthString();
    billNumber.setTag("01");
    billNumber.setValue("12345");

    final TagLengthString customerLabel = new TagLengthString();
    customerLabel.setTag("06");
    customerLabel.setValue("fghij");

    final TagLengthString loyaltyNumber = new TagLengthString();
    loyaltyNumber.setTag("04");
    loyaltyNumber.setValue("54321");

    final TagLengthString mobileNumber = new TagLengthString();
    mobileNumber.setTag("02");
    mobileNumber.setValue("67890");

    final TagLengthString purposeTransaction = new TagLengthString();
    purposeTransaction.setTag("08");
    purposeTransaction.setValue("pqres");

    final TagLengthString referenceLabel = new TagLengthString();
    referenceLabel.setTag("05");
    referenceLabel.setValue("abcde");

    final TagLengthString storeLabel = new TagLengthString();
    storeLabel.setTag("03");
    storeLabel.setValue("09876");

    final TagLengthString terminalLabel = new TagLengthString();
    terminalLabel.setTag("07");
    terminalLabel.setValue("klmno");

    final TagLengthString paymentSystemSpecific = new TagLengthString();
    paymentSystemSpecific.setTag("50");
    paymentSystemSpecific.setValue("ijkl");

    final TagLengthString rFUforEMVCo = new TagLengthString();
    rFUforEMVCo.setTag("10");
    rFUforEMVCo.setValue("abcd");

    final AdditionalDataFieldValue additionalDataFieldValue = new AdditionalDataFieldValue();
    additionalDataFieldValue.setAdditionalConsumerDataRequest(additionalConsumerDataRequest);
    additionalDataFieldValue.setBillNumber(billNumber);
    additionalDataFieldValue.setCustomerLabel(customerLabel);
    additionalDataFieldValue.setLoyaltyNumber(loyaltyNumber);
    additionalDataFieldValue.setMobileNumber(mobileNumber);
    additionalDataFieldValue.setPurposeTransaction(purposeTransaction);
    additionalDataFieldValue.setReferenceLabel(referenceLabel);
    additionalDataFieldValue.setStoreLabel(storeLabel);
    additionalDataFieldValue.setTerminalLabel(terminalLabel);
    additionalDataFieldValue.addPaymentSystemSpecific(paymentSystemSpecific);
    additionalDataFieldValue.addRFUforEMVCo(rFUforEMVCo);

    final AdditionalDataField additionalDataField = new AdditionalDataField();
    additionalDataField.setValue(additionalDataFieldValue);
    return additionalDataField;
  }

}
