/**
 * <p>
 * This code is provided under the
 * <a href="https://opensource.org/licenses/MIT">MIT License</a>:
 * </p>
 *
 * <pre>
 *  MIT License
 *
 *  Copyright (c) 2023 Luan Nadaletti
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 * </pre>
 *
 * <p>
 * SQLUndo is a software that allows receiving a complete SQL script and
 * performing the reverse operations of each query. It is designed to identify
 * and execute reversal operations on queries that cannot be executed more than
 * once due to primary key (PK) constraints and other restrictions.
 * </p>
 *
 * @author Luan Nadaletti
 * @version 1.0.0
 */
package com.sqlundo;
