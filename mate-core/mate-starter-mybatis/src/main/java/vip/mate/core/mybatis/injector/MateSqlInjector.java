/*
 * Copyright (c) 2019-2029, Dreamlu 卢春梦 (596392912@qq.com & www.dreamlu.net).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vip.mate.core.mybatis.injector;

import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

/**
 * 自定义的 sql 注入
 *
 * @author L.cm
 */
public class MateSqlInjector extends DefaultSqlInjector {

//	@Override
//	public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
//		List<AbstractMethod> methodList = new ArrayList<>();
//		methodList.add(new InsertBatch());
//		methodList.add(new InsertIgnore());
//		methodList.add(new InsertIgnoreBatch());
//		methodList.add(new Replace());
//		methodList.add(new ReplaceBatch());
//		methodList.addAll(super.getMethodList(mapperClass));
//		return Collections.unmodifiableList(methodList);
//	}
}
