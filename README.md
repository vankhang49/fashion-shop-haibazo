# fashion-shop-haibazo

fashion-shop-api
﻿

GET http://localhost:8080/api/public/products?pageSize=&nameSearch=&categoryName=&styleName=&colorName=&size=&nameSort=price&sortDirection=

Calling API gets a list of products in Slice format, adds filtering by fields and sorting by price and date added.
_Slice: pageSize default 12
_Filter: nameSearch(productName), categoryName, styleName, colorName, size, price between(defaul 0 to 9999999999)
_Sort by:
+ sortName: price, dateCreate
+ sortDirection: asc, desc


GET
http://localhost:8080/api/public/products/17

Api call to get product by productId

﻿

POST
http://localhost:8080/api/public/products

Api call to save new product along with its detailed products

﻿

PUT
http://localhost:8080/api/public/products

Api call to update new product along with its detailed products

﻿

PATCH
http://localhost:8080/api/public/products/18/soft-delete

The Api call to put the product in the list is removed

﻿

PATCH
http://localhost:8080/api/public/products/18/restore

Api call to restore product from deleted list

﻿

DELETE
http://localhost:8080/api/public/products/18

Api call to completely remove the product from the database

﻿

PATCH
http://localhost:8080/api/public/pricing/35/soft-delete

The Api call to put the pricing in the list is removed

﻿

PATCH
http://localhost:8080/api/public/pricing/35/restore

Api call to restore pricing from deleted list

﻿

DELETE
http://localhost:8080/api/public/pricing/35

Api call to completely remove the pricing from the database

﻿

GET
http://localhost:8080/api/public/categories

Api call to get list of categories

﻿

GET
http://localhost:8080/api/public/styles

Api call to get list of styles

﻿

GET
http://localhost:8080/api/public/colors

Api call to get list of colors

﻿

GET
http://localhost:8080/api/public/promotions

Api call to get list of promotions