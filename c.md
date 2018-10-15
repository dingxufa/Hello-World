





### 1.NULL vs nullptr

如果要表示一个指针为空，我们条件反射肯定会这么写：

```c++
int *p = NULL;
```

但这样写是有问题的 eg:

```c++
#include <iostream>
#include <string>
using namespace std;

void func(int* num)
{
    cout << "this is the ptr function..." << endl;
}

void func(int num)
{
    cout << "this is the normal function..." << endl;
}

void main(int argc, char** argv)
{
	func(NULL);
	func(nullptr);
	func(0);
	func(0);
	func(0);
	func(0);
}


------------------------------- debug 中显示结果
this is the normal function...   //func(NULL);
this is the ptr function...		//	func(nullptr);
this is the normal function...   //func(0);
this is the normal function...
this is the normal function...
this is the normal function...
    
  
```

>在编译器进行解释程序时，NULL会被直接解释成0，所以这里的参数根本就不是大家所想的NULL，参数已经被编译器偷偷换成了0，0是整数啊，所以调用的是第二个函数。  
>
>nullptr在C++11中就是代表空指针，不能被转换成数字