#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {
    int i;
    pid_t processId = 0;
    
    processId = fork();
    
    for (i = 0; i < 1000; i++) {
        printf("[%d]==> %d\n", processId, i);
        int temp = rand() * rand();
    }
    printf("---------------------------\n");
    
    return 0;
}