#include <stdio.h>

void increment(int val) {
    val++;
}

struct Person {
    char* name;
    int age;
    float height;
};

void main() {
    int a = 5;

    // 1. Printing normal variable values
    printf("1. Value of a is %d\n", a);

    // 2. Printing memory addresses using the '&' (address-of) operator
    printf("2. Address of a is %p\n", &a);

    // 3. Printing the value stored inside the pointer itself (which is an address)
    int *a_ptr = &a;
    printf("3. Value of a is %d\n", *a_ptr);

    // 4. Pointer arithmetic
    int b[5] = {91, 92, 93, 94, 95};
    int *b_ptr = b;
    printf("4.1 Value of b[0] is %d\n", *b_ptr);
    b_ptr++;
    printf("4.2 Value of b[1] is %d\n", *b_ptr);
    b_ptr++;
    printf("4.3 Value of b[2] is %d\n", *b_ptr);
    b_ptr++;
    printf("4.4 Value of b[3] is %d\n", *b_ptr);
    b_ptr++;
    printf("4.5 Value of b[4] is %d\n", *b_ptr);

    // 5. Pass by value
    // In C, function arguments are passed by value. This means the function receives a copy of the argument, not the original variable.
    int c = 105;
    printf("5.1 Value of c before increment pass_by_value_fn is %d\n", c);
    increment(c);
    printf("5.2 Value of c after increment pass_by_value_fn is %d\n", c);

    // 7. Struct
    struct Person p1 = {"Alice", 32, 178.25};
    printf("7. Person p1 name is %s, age is %d, height is %.1f cm\n", p1.name, p1.age, p1.height);

    // 8. Pointer for Struct
    struct Person *p1_ptr = &p1;
    printf("8. Person p1 name is %s, age is %d, height is %.1f cm\n", p1_ptr->name, p1_ptr->age, p1_ptr->height);
}