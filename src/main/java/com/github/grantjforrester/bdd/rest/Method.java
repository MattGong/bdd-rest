/**
 * Copyright 2015 Grant Forrester
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.grantjforrester.bdd.rest;

/**
 * The HTTP methods supported by the <code>RestClient</code>.
 * See <a href="http://tools.ietf.org/html/rfc2616">RFC 2616</a>.
 */
public enum Method {
	HEAD,
	OPTIONS,
	GET,
	POST,
	PUT,
	DELETE,
	PATCH
}
