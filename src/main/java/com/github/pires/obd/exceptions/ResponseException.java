/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.pires.obd.exceptions;

/**
 * Generic message error
 *
 */
public class ResponseException extends RuntimeException {

    private String message;

    private String response;

    private String command;

    private boolean matchRegex;

    /**
     * <p>Constructor for ResponseException.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    protected ResponseException(String message) {
        this.message = message;
    }

    /**
     * <p>Constructor for ResponseException.</p>
     *
     * @param message a {@link java.lang.String} object.
     * @param matchRegex a boolean.
     */
    protected ResponseException(String message, boolean matchRegex) {
        this.message = message;
        this.matchRegex = matchRegex;
    }

    private static String clean(String s) {
        return s == null ? "" : s.replaceAll("\\s", "").toUpperCase();
    }

    /**
     * <p>isError.</p>
     *
     * @param response a {@link java.lang.String} object.
     * @return a boolean.
     */
    public boolean isError(String response) {
        this.response = response;
        if (matchRegex) {
            return clean(response).matches(clean(message));
        } else {
            return clean(response).contains(clean(message));
        }
    }

    /**
     * <p>Setter for the field <code>command</code>.</p>
     *
     * @param command a {@link java.lang.String} object.
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /** {@inheritDoc} */
    @Override
    public String getMessage() {
        return "Error running " + command + ", response: " + response;
    }

}
