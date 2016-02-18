print_sorted(char A[], int N) {
    int numObjects = 0;
    for (int i = 0 ; i < N; i++) {
        if (A[i] = = '\0') {
            numObjects++;
        }
    }

    int *indirectArray = malloc(sizeof(int) * numObjects);
    indirectArray[0] = 0;
    int offset = 0;
    int numLeft = numObjects -1;
    int i = 0;
    int j = 1;
    while ( numLeft > 0 ) {
        if (A[i] == '\0') {
            indirectArray[j++] = i + 1;
        }
        i++;
    }

    sort (indirectArray, // compare char sequences in A);

}
