/**
 * Copyright 2010-present Facebook
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.facebook.android;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Bundle;
import com.facebook.internal.Utility;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.*;

/**
 * Utility class supporting the Facebook Object.
 * <p/>
 * THIS CLASS SHOULD BE CONSIDERED DEPRECATED.
 * <p/>
 * All public members of this class are intentionally deprecated.
 * New code should instead use
 * {@link com.facebook.Request}
 * <p/>
 * Adding @Deprecated to this class causes warnings in other deprecated classes
 * that reference this one.  That is the only reason this entire class is not
 * deprecated.
 *
 * @devDocDeprecated
 */
public final class Util {

    private final static String UTF8 = "UTF-8";

    @Deprecated
    public static Bundle decodeUrl(String s) {
        Bundle params = new Bundle();
        if (s != null) {
            String array[] = s.split("&");
            for (String parameter : array) {
                String v[] = parameter.split("=");

                try {
                    if (v.length == 2) {
                        params.putString(URLDecoder.decode(v[0], UTF8),
                                         URLDecoder.decode(v[1], UTF8));
                    } else if (v.length == 1) {
                        params.putString(URLDecoder.decode(v[0], UTF8), "");
                    }
                } catch (UnsupportedEncodingException e) {
                    // shouldn't happen
                }
            }
        }
        return params;
    }

    /**
     * Parse a URL query and fragment parameters into a key-value bundle.
     *
     * @param url the URL to parse
     * @return a dictionary bundle of keys and values
     */
    @Deprecated
    public static Bundle parseUrl(String url) {
        // hack to prevent MalformedURLException
        url = url.replace("fbconnect", "http");
        try {
            URL u = new URL(url);
            Bundle b = decodeUrl(u.getQuery());
            b.putAll(decodeUrl(u.getRef()));
            return b;
        } catch (MalformedURLException e) {
            return new Bundle();
        }
    }
}
