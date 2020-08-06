package com.emv.qrcode.decoder.mpm;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.decoder.DecodeIterator;
import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.model.mpm.AdditionalDataFieldTemplate;
import com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate;
import com.emv.qrcode.model.mpm.MerchantInformationLanguageTemplate;
import com.emv.qrcode.model.mpm.MerchantPresentedMode;
import com.emv.qrcode.model.mpm.UnreservedTemplate;
import com.emv.qrcode.model.mpm.constants.MerchantPresentedModeCodes;

// @formatter:off
public final class MerchantPresentedModeDecoder extends Decoder<MerchantPresentedMode> {

  private static final Map<String, Entry<Class<?>, BiConsumer<MerchantPresentedMode, ?>>> mapConsumers = new HashMap<>();

  static {
    mapConsumers.put(MerchantPresentedModeCodes.ID_PAYLOAD_FORMAT_INDICATOR, consumerTagLengthValue(TagLengthString.class, MerchantPresentedMode::setPayloadFormatIndicator));
    mapConsumers.put(MerchantPresentedModeCodes.ID_POINT_OF_INITIATION_METHOD, consumerTagLengthValue(TagLengthString.class, MerchantPresentedMode::setPointOfInitiationMethod));
    mapConsumers.put(MerchantPresentedModeCodes.ID_MERCHANT_CATEGORY_CODE, consumerTagLengthValue(TagLengthString.class, MerchantPresentedMode::setMerchantCategoryCode));
    mapConsumers.put(MerchantPresentedModeCodes.ID_TRANSACTION_CURRENCY, consumerTagLengthValue(TagLengthString.class, MerchantPresentedMode::setTransactionCurrency));
    mapConsumers.put(MerchantPresentedModeCodes.ID_TRANSACTION_AMOUNT, consumerTagLengthValue(TagLengthString.class, MerchantPresentedMode::setTransactionAmount));
    mapConsumers.put(MerchantPresentedModeCodes.ID_TIP_OR_CONVENIENCE_INDICATOR, consumerTagLengthValue(TagLengthString.class, MerchantPresentedMode::setTipOrConvenienceIndicator));
    mapConsumers.put(MerchantPresentedModeCodes.ID_VALUE_OF_CONVENIENCE_FEE_FIXED, consumerTagLengthValue(TagLengthString.class, MerchantPresentedMode::setValueOfConvenienceFeeFixed));
    mapConsumers.put(MerchantPresentedModeCodes.ID_VALUE_OF_CONVENIENCE_FEE_PERCENTAGE, consumerTagLengthValue(TagLengthString.class, MerchantPresentedMode::setValueOfConvenienceFeePercentage));
    mapConsumers.put(MerchantPresentedModeCodes.ID_COUNTRY_CODE, consumerTagLengthValue(TagLengthString.class, MerchantPresentedMode::setCountryCode));
    mapConsumers.put(MerchantPresentedModeCodes.ID_MERCHANT_NAME, consumerTagLengthValue(TagLengthString.class, MerchantPresentedMode::setMerchantName));
    mapConsumers.put(MerchantPresentedModeCodes.ID_MERCHANT_CITY, consumerTagLengthValue(TagLengthString.class, MerchantPresentedMode::setMerchantCity));
    mapConsumers.put(MerchantPresentedModeCodes.ID_POSTAL_CODE, consumerTagLengthValue(TagLengthString.class, MerchantPresentedMode::setPostalCode));
    mapConsumers.put(MerchantPresentedModeCodes.ID_CRC, consumerTagLengthValue(TagLengthString.class, MerchantPresentedMode::setCRC));
    mapConsumers.put(MerchantPresentedModeCodes.ID_ADDITIONAL_DATA_FIELD_TEMPLATE, consumerTagLengthValue(AdditionalDataFieldTemplate.class, MerchantPresentedMode::setAdditionalDataField));
    mapConsumers.put(MerchantPresentedModeCodes.ID_MERCHANT_INFORMATION_LANGUAGE_TEMPLATE, consumerTagLengthValue(MerchantInformationLanguageTemplate.class, MerchantPresentedMode::setMerchantInformationLanguage));
    mapConsumers.put(MerchantPresentedModeCodes.ID_MERCHANT_ACCOUNT_INFORMATION, consumerTagLengthValue(MerchantAccountInformationTemplate.class, MerchantPresentedMode::addMerchantAccountInformation));
    mapConsumers.put(MerchantPresentedModeCodes.ID_RFU_FOR_EMVCO, consumerTagLengthValue(TagLengthString.class, MerchantPresentedMode::addRFUforEMVCo));
    mapConsumers.put(MerchantPresentedModeCodes.ID_UNRESERVED_TEMPLATES, consumerTagLengthValue(UnreservedTemplate.class, MerchantPresentedMode::addUnreserved));
  }

  public MerchantPresentedModeDecoder(final String source) {
    super(source);
  }

  @Override
  @SuppressWarnings({ "unchecked", "rawtypes" })
  protected MerchantPresentedMode decode() {
    final MerchantPresentedMode result = new MerchantPresentedMode();

    iterator.forEachRemaining(value -> {
      final String tag = derivateId(value.substring(0, DecodeIterator.ID_WORD_COUNT));

      final Entry<Class<?>, BiConsumer<MerchantPresentedMode, ?>> entry = mapConsumers.get(tag);

      final Class<?> clazz = entry.getKey();

      final BiConsumer consumer = entry.getValue();

      consumer.accept(result, Decoder.decode(value, clazz));
    });

    return result;
  }

  private String derivateId(final String id) {

    if (betweenAccountInformationRange(id)) {
      return MerchantPresentedModeCodes.ID_MERCHANT_ACCOUNT_INFORMATION;
    }

    if (betweenRFUForEMVCORange(id)) {
      return MerchantPresentedModeCodes.ID_RFU_FOR_EMVCO;
    }

    if (betweenUnreservedTemplatesRange(id)) {
      return MerchantPresentedModeCodes.ID_UNRESERVED_TEMPLATES;
    }

    return id;
  }

  private boolean betweenAccountInformationRange(final String value) {
    return value.compareTo(MerchantPresentedModeCodes.ID_MERCHANT_ACCOUNT_INFORMATION_RANGE_START) >= 0
        && value.compareTo(MerchantPresentedModeCodes.ID_MERCHANT_ACCOUNT_INFORMATION_RANGE_END) <= 0;
  }

  private boolean betweenRFUForEMVCORange(final String value) {
    return value.compareTo(MerchantPresentedModeCodes.ID_RFU_FOR_EMVCO_RANGE_START) >= 0
        && value.compareTo(MerchantPresentedModeCodes.ID_RFU_FOR_EMVCO_RANGE_END) <= 0;
  }

  private boolean betweenUnreservedTemplatesRange(final String value) {
    return value.compareTo(MerchantPresentedModeCodes.ID_UNRESERVED_TEMPLATES_RANGE_START) >= 0
        && value.compareTo(MerchantPresentedModeCodes.ID_UNRESERVED_TEMPLATES_RANGE_END) <= 0;
  }

}
// @formatter:on