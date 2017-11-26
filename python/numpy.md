# Python numpy snippets
All the tutorials below assume that you have numpy imported as:
```
import numpy as np
```
Get indices for the `k` smallest array elements:
```
idx = np.argpartition(my_array, k)
```
