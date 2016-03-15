package com.uww.messaging.validator;

import com.google.common.base.Strings;
import com.uww.messaging.display.UserRegistration;

/**
 * Created by horvste on 1/18/16.
 */
public class UserRegistrationValidator {
    public static boolean validate(UserRegistration userRegistration) {
        for (Object obj : userRegistration.toArray()) {
            if (obj == null)
                return false;
            if (obj instanceof String) {
                if (Strings.isNullOrEmpty(((String) obj))) {
                    return false;
                }
            }
        }
        //TODO: More robust impl
        return true;
    }
}
