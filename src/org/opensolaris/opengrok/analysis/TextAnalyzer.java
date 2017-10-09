/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License (the "License").
 * You may not use this file except in compliance with the License.
 *
 * See LICENSE.txt included in this distribution for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at LICENSE.txt.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

 /*
 * Copyright (c) 2005, 2017, Oracle and/or its affiliates. All rights reserved.
 */
package org.opensolaris.opengrok.analysis;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.TokenStream;
import org.opensolaris.opengrok.search.QueryBuilder;
import org.opensolaris.opengrok.util.IOUtils;

public abstract class TextAnalyzer extends FileAnalyzer {

    public TextAnalyzer(FileAnalyzerFactory factory) {
        super(factory);
    }

    protected Reader getReader(InputStream stream) throws IOException {
        return IOUtils.createBOMStrippedReader(stream);
    }

    @Override
    protected TokenStream normalize(String fieldName, TokenStream in) {
        switch (fieldName) {
        case QueryBuilder.DEFS:        
        case QueryBuilder.REFS:
            return in;
        default:
        return new LowerCaseFilter(in);
        }
    }

}
