/*
 * Copyright 2015 Edward Zengeni.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.company.report.api;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * @author Edward Zengeni
 */
public class BasicReportModel implements Serializable {
    
    private final String name;
    private final Integer count;
    private final Integer total;

    public BasicReportModel(String name, Integer count, Integer total) {
        this.name = name;
        this.count = count;
        this.total = total;
    }

    public String getName() {
        return name+" [N="+count+"]";
    }

    public Integer getCount() {
        return count;
    }

    public Integer getTotal() {
        return total;
    }
    
    public BigDecimal getPercentage(){
        if(count == 0 || total == 0){
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(((double) count/ (double)total) *100).round(new MathContext(2, RoundingMode.CEILING));
    }

    @Override
    public String toString() {
        return "BasicReportModel{" + "name=" + name + ", count=" + count + ", total=" + total + '}';
    }
    
    
}