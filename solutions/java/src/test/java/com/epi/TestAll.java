package com.epi;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class TestAll {

  static final String[] NO_ARGS = new String[0];

  static int maxTimePerMethod = 100000;

  @Rule
  public Timeout globalTimeout = new Timeout(maxTimePerMethod);

  @Before
  public void setup() {
    String timeoutString = System.getProperty("timeout");
    System.out.println("timeout = " + timeoutString);
    if (timeoutString != null) {
      globalTimeout = new Timeout(new Integer(timeoutString));
    }
  }

  @Test
  public void testAddOperatorsinString() {
    // TODO(AA): find implementation
    // AddOperatorsinString.main(noArgs);
  }

  @Test
  public void testAddingCredits() {
    AddingCredits.main(NO_ARGS);
  }

  @Test
  public void testAnagrams() {
    Anagrams.main(NO_ARGS);
  }

  @Test
  public void testAnonymousLetter() {
    AnonymousLetter.main(NO_ARGS);
  }

  @Test
  public void testApproximateSort() {
    ApproximateSort.main(NO_ARGS);
  }

  @Test
  public void testArbitrage() {
    Arbitrage.main(NO_ARGS);
  }

  @Test
  public void testBSTLowestCommonAncestor() {
    BSTLowestCommonAncestor.main(NO_ARGS);
  }

  @Test
  public void testBSTSortedOrderTemplate() {
    BSTSortedOrderTemplate.main(NO_ARGS);
  }

  @Test
  public void testBSTToSortedDoublyList() {
    BSTToSortedDoublyList.main(NO_ARGS);
  }

  @Test
  public void testBalancedBinaryTreeTemplate() {
    BalancedBinaryTreeTemplate.main(NO_ARGS);
  }

  @Test
  public void testBentleybsearch() {
    Bentleybsearch.main(NO_ARGS);
  }

  @Test
  public void testBigNumberMultiplication() {
    BigNumberMultiplication.main(NO_ARGS);
  }

  @Test
  public void testBiggestProductNMinus1() {
    BiggestProductNMinus1.main(NO_ARGS);
  }

  @Test
  public void testBiggestProductNMinus1Math() {
    BiggestProductNMinus1Math.main(NO_ARGS);
  }

  // @Test public void testBinaryOperator() { BinaryOperator.main(noArgs); }
  @Test
  public void testBinarySearchAiEqI() {
    BinarySearchAiEqI.main(NO_ARGS);
  }

  @Test
  public void testBinarySearchCircularArrayTemplate() {
    BinarySearchCircularArrayTemplate.main(NO_ARGS);
  }

  @Test
  public void testBinarySearchCircularArrayWithDuplicatesTemplate() {
    BinarySearchCircularArrayWithDuplicatesTemplate.main(NO_ARGS);
  }

  @Test
  public void testBinarySearchFirstKTemplate() {
    BinarySearchFirstKTemplate.main(NO_ARGS);
  }

  @Test
  public void testBinarySearchLargerKTemplate() {
    BinarySearchLargerKTemplate.main(NO_ARGS);
  }

  // @Test public void testBinarySearchTree() { BinarySearchTree.main(noArgs); }
  @Test
  public void testBinarySearchUnknownLengthTemplate() {
    String[] args = { "100" };
    BinarySearchUnknownLengthTemplate.main(args);
  }

  @Test
  public void testBinaryTreeLevelOrderTemplate() {
    BinaryTreeLevelOrderTemplate.main(NO_ARGS);
  }

  @Test
  public void testBinaryTreeLockTemplate() {
    BinaryTreeLockTemplate.main(NO_ARGS);
  }

  // @Test public void testBinaryTreePrototypeTemplate() {
  // BinaryTreePrototypeTemplate.main(noArgs); }
  // @Test public void testBinaryTreeUtils() { BinaryTreeUtils.main(noArgs); }
  // @Test public void testBinaryTreeWithParentPrototype() {
  // BinaryTreeWithParentPrototype.main(noArgs); }
  @Test
  public void testBuildBSTFromSortedArray() {
    BuildBSTFromSortedArray.main(NO_ARGS);
  }

  @Test
  public void testCanStringBePalindrome() {
    CanStringBePalindrome.main(NO_ARGS);
  }

  // @Test public void testCanStringBePalindromeHash() {
  // CanStringBePalindromeHash.main(noArgs); }
  // @Test public void testCanStringBePalindromeSorting() {
  // CanStringBePalindromeSorting.main(noArgs); }
  @Test
  public void testCelebrityFinding() {
    CelebrityFinding.main(NO_ARGS);
  }

  @Test
  public void testCheckingCycle() {
    CheckingCycle.main(NO_ARGS);
  }

  @Test
  public void testCheckingCycleAlternative() {
    CheckingCycleAlternative.main(NO_ARGS);
  }

  @Test
  public void testCircularQueueTemplate() {
    CircularQueueTemplate.main(NO_ARGS);
  }

  @Test
  public void testCloseSearch() {
    CloseSearch.main(NO_ARGS);
  }

  @Test
  public void testClosestIntSameBits() {
    ClosestIntSameBits.main(NO_ARGS);
  }

  @Test
  public void testClosestPairPoints() {
    String[] args = { "500" };
    ClosestPairPoints.main(args);
  }

  @Test
  public void testClosestPalindrome() {
    ClosestPalindrome.main(NO_ARGS);
  }

  @Test
  public void testClosestStars() {
    ClosestStars.main(NO_ARGS);
  }

  @Test
  public void testClosestToMedian() {
    String[] args = { "100", "10" };
    ClosestToMedian.main(args);
  }

  @Test
  public void testCollatzConjecture() {
    CollatzConjecture.main(NO_ARGS);
  }

  @Test
  public void testCompareKthLargestInHeap() {
    String[] args = { "100" };
    CompareKthLargestInHeap.main(args);
  }

  @Test
  public void testCompletionSearch() {
    CompletionSearch.main(NO_ARGS);
  }

  @Test
  public void testComputingBinomialCoefficients() {
    ComputingBinomialCoefficients.main(NO_ARGS);
  }

  @Test
  public void testComputingXPowN() {
    ComputingXPowN.main(NO_ARGS);
  }

  @Test
  public void testConnectLeavesBinaryTreeTemplate() {
    ConnectLeavesBinaryTreeTemplate.main(NO_ARGS);
  }

  @Test
  public void testConvertBase() {
    ConvertBase.main(NO_ARGS);
  }

  @Test
  public void testCopyingPostingsList() {
    // TODO(AA): wait for alex to come back on type definition of NodeT
    CopyingPostingsList.main(NO_ARGS);
  }

  @Test
  public void testCountInversions() {
    String[] args = { "100" };
    CountInversions.main(args);
  }

  @Test
  public void testCount_occurrences_in_sentence() {
    CountOccurrencesInSentence.main(NO_ARGS);
  }

  @Test
  public void testCounting_sort() {
    CountingSort.main(NO_ARGS);
  }

  @Test
  public void testDeletionListTemplate() {
    DeletionListTemplate.main(NO_ARGS);
  }

  @Test
  public void testDescendantAndAncestor() {
    DescendantAndAncestor.main(NO_ARGS);
  }

  @Test
  public void testDivision() {
    Division.main(NO_ARGS);
  }

  @Test
  public void testDoors() {
    Doors.main(NO_ARGS);
  }

  // @Test public void testDoublyLinkedListPrototypeTemplate() {
  // DoublyLinkedListPrototypeTemplate.main(noArgs); }
  @Test
  public void testDrawingSkylines() {
    DrawingSkylines.main(NO_ARGS);
  }

  @Test
  public void testDutchNationalFlag() {
    DutchNationalFlag.main(NO_ARGS);
  }

  @Test
  public void testEliasCoding() {
    EliasCoding.main(NO_ARGS);
  }

  @Test
  public void testEliminate_duplicate() {
    EliminateDuplicate.main(NO_ARGS);
  }

  @Test
  public void testEquivClasses() {
    EquivClasses.main(NO_ARGS);
  }

  @Test
  public void testEvenOddMergeLinkedListTemplate() {
    EvenOddMergeLinkedListTemplate.main(NO_ARGS);
  }

  @Test
  public void testExteriorBinaryTreeTemplate() {
    ExteriorBinaryTreeTemplate.main(NO_ARGS);
  }

  @Test
  public void testFindElementAppearsOnce() {
    FindElementAppearsOnce.main(NO_ARGS);
  }

  @Test
  public void testFindKLargestBST() {
    FindKLargestBST.main(NO_ARGS);
  }

  @Test
  public void testFindKthElementInTwoSortedArraysTemplate() {
    FindKthElementInTwoSortedArraysTemplate.main(NO_ARGS);
  }

  @Test
  public void testFindMissingAndDuplicate() {
    FindMissingAndDuplicate.main(NO_ARGS);
  }

  @Test
  public void testFindMissingAndDuplicateXOR() {
    FindMissingAndDuplicateXOR.main(NO_ARGS);
  }

  @Test
  public void testFindingMinMax() {
    FindingMinMax.main(NO_ARGS);
  }

  @Test
  public void testGCD() {
    GCD.main(NO_ARGS);
  }

  @Test
  public void testGassingUp() {
    GassingUp.main(NO_ARGS);
  }

  @Test
  public void testGaussianElimination() {
    GaussianElimination.main(NO_ARGS);
  }

  @Test
  public void testGaussianPrimes() {
    String[] args = { "35" };
    GaussianPrimes.main(args);
  }

  @Test
  public void testGeneratingABSqrt2() {
    GeneratingABSqrt2.main(NO_ARGS);
  }

  @Test
  public void testHashDictionary() {
    HashDictionary.main(NO_ARGS);
  }

  @Test
  public void testHeightDetermination() {
    HeightDetermination.main(NO_ARGS);
  }

  @Test
  public void testHouseMajority() {
    HouseMajority.main(NO_ARGS);
  }

  @Test
  public void testHuffmanEncoding() {
    HuffmanEncoding.main(NO_ARGS);
  }

  @Test
  public void testImageCompression() {
    ImageCompression.main(NO_ARGS);
  }

  @Test
  public void testIndirect_sort() {
    try {
      IndirectSort.main(NO_ARGS);
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testInorderTraversalNoStackTemplate() {
    InorderTraversalNoStackTemplate.main(NO_ARGS);
  }

  @Test
  public void testInorderTraversalWithParentTemplate() {
    InorderTraversalWithParentTemplate.main(NO_ARGS);
  }

  @Test
  public void testInterconvertingStringInteger() {
    InterconvertingStringInteger.main(NO_ARGS);
  }

  @Test
  public void testIntersectRectangle() {
    IntersectRectangle.main(NO_ARGS);
  }

  @Test
  public void testIntersect_sorted_arrays() {
    IntersectSortedArrays.main(NO_ARGS);
  }

  // @Test public void testIntersect_sorted_arrays1() {
  // Intersect_sorted_arrays1.main(noArgs); }
  // @Test public void testIntersect_sorted_arrays2() {
  // Intersect_sorted_arrays2.main(noArgs); }
  // @Test public void testIntersect_sorted_arrays3() {
  // Intersect_sorted_arrays3.main(noArgs); }
  @Test
  public void testIsBinaryTreeABST() {
    IsBinaryTreeABST.main(NO_ARGS);
  }

  @Test
  public void testIsBinaryTreeABSTConstSpace() {
    IsBinaryTreeABSTConstSpace.main(NO_ARGS);
  }

  @Test
  public void testJobAssignment() {
    JobAssignment.main(NO_ARGS);
  }

  @Test
  public void testKBalancedBinaryTreeTemplate() {
    KBalancedBinaryTreeTemplate.main(NO_ARGS);
  }

  @Test
  public void testKThNodeBinaryTreeTemplate() {
    KThNodeBinaryTreeTemplate.main(NO_ARGS);
  }

  @Test
  public void testKthElementStreaming() {
    KthElementStreaming.main(NO_ARGS);
  }

  @Test
  public void testKthLargestElementLargeN() {
    String[] args = { "100", "10" };
    KthLargestElementLargeN.main(args);
  }

  @Test
  public void testKthLargestElementTemplate() {
    KthLargestElementTemplate.main(NO_ARGS);
  }

  @Test
  public void testLRUCache() {
    LRUCache.main(NO_ARGS);
  }

  @Test(expected = ArrayIndexOutOfBoundsException.class)
  public void testLargeArray() {
    LargeArray.main(NO_ARGS);
  }

  // @Test public void testLargestRectangleUnderSkyline() {
  // LargestRectangleUnderSkyline.main(noArgs); }
  @Test
  public void testLazyInit() {
    LazyInit.main(NO_ARGS);
  }

  @Test
  public void testLevenshteinDistance() {
    LevenshteinDistance.main(NO_ARGS);
  }

  @Test
  public void testLineMostPoints() {
    String[] args = { "50" };
    LineMostPoints.main(args);
  }

  // @Test public void testLinkedListPrototypeTemplate() {
  // LinkedListPrototypeTemplate.main(noArgs); }
  @Test
  public void testLinked_list() {
    LinkedList.main(NO_ARGS);
  }

  @Test
  public void testLoadBalancing() {
    LoadBalancing.main(NO_ARGS);
  }

  @Test
  public void testLongestIncreasingSubarray() {
    LongestIncreasingSubarray.main(NO_ARGS);
  }

  // @Test public void testLongestNondecreasingSubsequenceN2() {
  // LongestNondecreasingSubsequenceN2.main(noArgs); }
  // @Test public void testLongestNondecreasingSubsequenceNlogn() {
  // LongestNondecreasingSubsequenceNlogn.main(noArgs); }
  @Test
  public void testLongestSubarrayK() {
    String[] args = { "100" };
    LongestSubarrayK.main(args);
  }

  @Test
  public void testLowestCommonAncestorHashTemplate() {
    LowestCommonAncestorHashTemplate.main(NO_ARGS);
  }

  @Test
  public void testLowestCommonAncestorNoParentTemplate() {
    LowestCommonAncestorNoParentTemplate.main(NO_ARGS);
  }

  @Test
  public void testLowestCommonAncestorTemplate() {
    LowestCommonAncestorTemplate.main(NO_ARGS);
  }

  @Test
  public void testMatrixRotation() {
    MatrixRotation.main(NO_ARGS);
  }

  @Test
  public void testMatrixRotationConstant() {
    MatrixRotationConstant.main(NO_ARGS);
  }

  @Test
  public void testMatrixRotationNaive() {
    MatrixRotationNaive.main(NO_ARGS);
  }

  @Test
  public void testMatrixSearch() {
    MatrixSearch.main(NO_ARGS);
  }

  @Test
  public void testMaxDifferenceKPairs() {
    MaxDifferenceKPairs.main(NO_ARGS);
  }

  @Test
  public void testMaxDifferenceUnlimitedPairs() {
    MaxDifferenceUnlimitedPairs.main(NO_ARGS);
  }

  @Test
  public void testMaxSubmatrixRectangle() {
    MaxSubmatrixRectangle.main(NO_ARGS);
  }

  // @Test public void testMaxSubmatrixRectangleBruteForce() {
  // MaxSubmatrixRectangleBruteForce.main(noArgs); }
  @Test
  public void testMaxSubmatrixRectangleImproved() {
    String[] args = { "16", "32" };
    MaxSubmatrixRectangleImproved.main(args);
  }

  @Test
  public void testMaxSubmatrixSquare() {
    MaxSubmatrixSquare.main(NO_ARGS);
  }

  @Test
  public void testMaxSumSubarray() {
    String[] args = { "100" };
    MaxSumSubarray.main(args);
  }

  @Test
  public void testMaximumSubarrayInCircularArray() {
    String[] args = { "100" };
    // TODO(AA): ArrayIndexOutOfBoundsException: 1
    // com.epi.MaximumSubarrayInCircularArray.main(MaximumSubarrayInCircularArray.java:83)
    MaximumSubarrayInCircularArray.main(args);
  }

  @Test
  public void testMaximumSubarrayInCircularArrayConstantSpace() {
    String[] args = { "100" };
    MaximumSubarrayInCircularArrayConstantSpace.main(args);
  }

  @Test
  public void testMedianSortedCircularLinkedListTemplate() {
    MedianSortedCircularLinkedListTemplate.main(NO_ARGS);
  }

  // @Test public void testMergeSortedArrays() { MergeSortedArrays.main(noArgs);
  // }
  @Test
  public void testMergeSortedListsTemplate() {
    MergeSortedListsTemplate.main(NO_ARGS);
  }

  @Test
  public void testMergeTwoBSTs() {
    MergeTwoBSTs.main(NO_ARGS);
  }

  @Test
  public void testMinimumDistance3SortedArrays() {
    String[] args = { "3" };
    MinimumDistance3SortedArrays.main(args);
  }

  @Test
  public void testMinimumSubarrayDifference() {
    MinimumSubarrayDifference.main(NO_ARGS);
  }

  @Test
  public void testMinimumWaitingTime() {
    MinimumWaitingTime.main(NO_ARGS);
  }

  @Test
  public void testMissingElement() {
    MissingElement.main(NO_ARGS);
  }

  @Test
  public void testMultibetCardColorGame() {
    MultibetCardColorGame.main(NO_ARGS);
  }

  @Test
  public void testMultiplyShiftAdd() {
    MultiplyShiftAdd.main(NO_ARGS);
  }

  @Test
  public void testNearestRepetition() {
    String[] args = { "100" };
    NearestRepetition.main(args);
  }

  @Test
  public void testNearestRestaurant() {
    NearestRestaurant.main(NO_ARGS);
  }

  @Test
  public void testNextPermutation() {
    NextPermutation.main(NO_ARGS);
  }

  // @Test public void testNodeT() { NodeT.main(noArgs); }
  @Test
  public void testNonUniformRandomNumberGeneration() {
    NonUniformRandomNumberGeneration.main(NO_ARGS);
  }

  @Test
  public void testNormalizedPathnames() {
    NormalizedPathnames.main(NO_ARGS);
  }

  @Test
  public void testNumberWays() {
    NumberWays.main(NO_ARGS);
  }

  @Test
  public void testNumberWaysObstacles() {
    NumberWaysObstacles.main(NO_ARGS);
  }

  // @Test public void testObjectWrapper() { ObjectWrapper.main(noArgs); }
  @Test
  public void testOfflineMinimum() {
    OfflineMinimum.main(NO_ARGS);
  }

  @Test
  public void testOfflineSampling() {
    OfflineSampling.main(NO_ARGS);
  }

  @Test
  public void testOnlineMedian() {
    OnlineMedian.main(NO_ARGS);
  }

  @Test
  public void testOnlineSampling() {
    OnlineSampling.main(NO_ARGS);
  }

  @Test
  public void testOverlappingListsNoCycle() {
    OverlappingListsNoCycle.main(NO_ARGS);
  }

  @Test
  public void testOverlappingListsTemplate() {
    OverlappingListsTemplate.main(NO_ARGS);
  }

  @Test
  public void testPaintingIterative() {
    PaintingIterative.main(NO_ARGS);
  }

  @Test
  public void testPaintingRecursive() {
    PaintingRecursive.main(NO_ARGS);
  }

  // @Test public void testPair() { Pair.main(noArgs); }
  @Test
  public void testPalindromeLinkedListTemplate() {
    PalindromeLinkedListTemplate.main(NO_ARGS);
  }

  @Test
  public void testParity() {
    Parity.main(NO_ARGS);
  }

  // @Test public void testParity1() { Parity1.main(noArgs); }
  // @Test public void testParity2() { Parity2.main(noArgs); }
  // @Test public void testParity3() { Parity3.main(noArgs); }
  // @Test public void testParity4() { Parity4.main(noArgs); }
  @Test
  public void testPermutationArray() {
    PermutationArray.main(NO_ARGS);
  }

  // @Test public void testPermutationArray1() { PermutationArray1.main(noArgs);
  // }
  // @Test public void testPermutationArray2() { PermutationArray2.main(noArgs);
  // }
  @Test
  public void testPhoneMnemonic() {
    PhoneMnemonic.main(NO_ARGS);
  }

  @Test
  public void testPickingUpCoins() {
    PickingUpCoins.main(NO_ARGS);
  }

  @Test
  public void testPickingUpCoinsDontLose() {
    PickingUpCoinsDontLose.main(NO_ARGS);
  }

  @Test
  public void testPlanningFishing() {
    PlanningFishing.main(NO_ARGS);
  }

  @Test
  public void testPoints_covering_intervals() {
    PointsCoveringIntervals.main(NO_ARGS);
  }

  @Test
  public void testPoints_covering_intervals_alternative() {
    PointsCoveringIntervalsAlternative.main(NO_ARGS);
  }

  // @Test public void testPostingsListPrototype() {
  // PostingsListPrototype.main(noArgs); }
  @Test
  public void testPowerSet() {
    PowerSet.main(NO_ARGS);
  }

  @Test
  public void testPowerSetAlternative() {
    PowerSetAlternative.main(NO_ARGS);
  }

  @Test
  public void testPrettyPrinting() {
    PrettyPrinting.main(NO_ARGS);
  }

  @Test
  public void testPrimeSieve() {
    String[] args = { "1000" };
    PrimeSieve.main(args);
  }

  @Test
  public void testQueueFromStacksTemplate() {
    QueueFromStacksTemplate.main(NO_ARGS);
  }

  @Test
  public void testQueueUsingTwoIntegers() {
    QueueUsingTwoIntegers.main(NO_ARGS);
  }

  @Test
  public void testQueueWithMax() {
    QueueWithMax.main(NO_ARGS);
  }

  @Test
  public void testQueueWithMaxUsingDeque() {
    QueueWithMaxUsingDeque.main(NO_ARGS);
  }

  @Test
  public void testRPN() {
    RPN.main(NO_ARGS);
  }

  @Test
  public void testRearrange() {
    Rearrange.main(NO_ARGS);
  }

  @Test
  public void testRebuildBSTPostorder() {
    RebuildBSTPostorder.main(NO_ARGS);
  }

  @Test
  public void testRebuildBSTPreorder() {
    RebuildBSTPreorder.main(NO_ARGS);
  }

  @Test
  public void testRebuildBSTPreorderBetter() {
    RebuildBSTPreorderBetter.main(NO_ARGS);
  }

  @Test
  public void testReconstructBinaryTreePostInOrdersTemplate() {
    ReconstructBinaryTreePostInOrdersTemplate.main(NO_ARGS);
  }

  @Test
  public void testReconstructBinaryTreePreInOrdersTemplate() {
    ReconstructBinaryTreePreInOrdersTemplate.main(NO_ARGS);
  }

  @Test
  public void testReconstructPreorderWithNullTemplate() {
    ReconstructPreorderWithNullTemplate.main(NO_ARGS);
  }

  @Test
  public void testRegularExpression() {
    RegularExpression.main(NO_ARGS);
  }

  @Test
  public void testRemoveKthLastListTemplate() {
    RemoveKthLastListTemplate.main(NO_ARGS);
  }

  @Test
  public void testRendering_calendar() {
    RenderingCalendar.main(NO_ARGS);
  }

  @Test
  public void testReplaceAndRemove() {
    ReplaceAndRemove.main(NO_ARGS);
  }

  @Test
  public void testReservoirSampling() {
    try {
      ReservoirSampling.main(NO_ARGS);
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testReverseBits() {
    ReverseBits.main(NO_ARGS);
  }

  @Test
  public void testReverseLinkedListIterativeTemplate() {
    ReverseLinkedListIterativeTemplate.main(NO_ARGS);
  }

  @Test
  public void testReverseLinkedListRecursiveTemplate() {
    ReverseLinkedListRecursiveTemplate.main(NO_ARGS);
  }

  @Test
  public void testReverseWords() {
    ReverseWords.main(NO_ARGS);
  }

  @Test
  public void testRoadNetwork() {
    String[] args = { "50" };
    RoadNetwork.main(args);
  }

  @Test
  public void testRobotBattery() {
    String[] args = { "100" };
    RobotBattery.main(args);
  }

  // @Test public void testRotateArray() { RotateArray.main(noArgs); /}
  // @Test public void testRotateArrayPermutation() {
  // RotateArrayPermutation.main(noArgs); }
  @Test
  public void testRotateArrayTest() {
    RotateArrayTest.main(NO_ARGS);
  }

  @Test
  public void testRunLengthCompression() {
    RunLengthCompression.main(NO_ARGS);
  }

  @Test
  public void testScoreCombination() {
    ScoreCombination.main(NO_ARGS);
  }

  @Test
  public void testScorePermutation() {
    ScorePermutation.main(NO_ARGS);
  }

  @Test
  public void testSearchAPairSortedArrayTemplate() {
    SearchAPairSortedArrayTemplate.main(NO_ARGS);
  }

  @Test
  public void testSearchBSTFirstLargerK() {
    SearchBSTFirstLargerK.main(NO_ARGS);
  }

  @Test
  public void testSearchBSTForFirstOccurrenceIterative() {
    SearchBSTForFirstOccurrenceIterative.main(NO_ARGS);
  }

  @Test
  public void testSearchBSTForFirstOccurrenceRecursive() {
    SearchBSTForFirstOccurrenceRecursive.main(NO_ARGS);
  }

  @Test
  public void testSearchMajority() {
    SearchMajority.main(NO_ARGS);
  }

  @Test
  public void testSearchMaze() {
    SearchMaze.main(NO_ARGS);
  }

  @Test
  public void testSearchMinFirstBST() {
    SearchMinFirstBST.main(NO_ARGS);
  }

  @Test
  public void testSearchPostingsListIterative() {
    // TODO(AA): wit for alex to come back on NodeT
    SearchPostingsListIterative.main(NO_ARGS);
  }

  @Test
  public void testSearchPostingsListRecursive() {
    // TODO(AA): wit for alex to come back on NodeT
    SearchPostingsListRecursive.main(NO_ARGS);
  }

  @Test
  public void testSearch_frequent_items() {
    String[] args = { "100", "10" };
    SearchFrequentItems.main(args);
  }

  @Test
  public void testShortestPathFewestEdges() {
    ShortestPathFewestEdges.main(NO_ARGS);
  }

  @Test
  public void testShortestUniquePrefix() {
    ShortestUniquePrefix.main(NO_ARGS);
  }

  @Test
  public void testSlidingWindow() {
    SlidingWindow.main(NO_ARGS);
  }

  @Test
  public void testSmallestSubarrayCoveringSet() {
    String[] args = { "100" };
    SmallestSubarrayCoveringSet.main(args);
  }

  // @Test public void testSmallestSubarrayCoveringSetStream() {
  // SmallestSubarrayCoveringSetStream.main(noArgs); }
  @Test
  public void testSortedListToBST() {
    SortedListToBST.main(NO_ARGS);
  }

  @Test
  public void testSpiralMatrix() {
    SpiralMatrix.main(NO_ARGS);
  }

  @Test
  public void testSpiralMatrixClockwise() {
    SpiralMatrixClockwise.main(NO_ARGS);
  }

  @Test
  public void testSpreadsheetEncoding() {
    SpreadsheetEncoding.main(NO_ARGS);
  }

  @Test
  public void testSquareRoot() {
    SquareRoot.main(NO_ARGS);
  }

  @Test
  public void testStableAssignment() {
    StableAssignment.main(NO_ARGS);
  }

  @Test
  public void testStackQueueUsingHeapTemplate() {
    StackQueueUsingHeapTemplate.main(NO_ARGS);
  }

  @Test
  public void testStackSorting() {
    String[] args = { "100" };
    StackSorting.main(args);
  }

  @Test
  public void testStackWithMaxImproved() {
    StackWithMaxImproved.main(NO_ARGS);
  }

  @Test
  public void testStackWithMaxTemplate() {
    StackWithMaxTemplate.main(NO_ARGS);
  }

  @Test
  public void testStringInMatrix() {
    StringInMatrix.main(NO_ARGS);
  }

  @Test
  public void testSubseqCover() {
    SubseqCover.main(NO_ARGS);
  }

  @Test
  public void testSuccessor() {
    Successor.main(NO_ARGS);
  }

  // @Test public void testSudokuCheck() { SudokuCheck.main(noArgs); }
  @Test
  public void testSudokuSolve() {
    SudokuSolve.main(NO_ARGS);
  }

  @Test
  public void testSwapBits() {
    SwapBits.main(NO_ARGS);
  }

  @Test
  public void testSymmetricBinaryTreeTemplate() {
    SymmetricBinaryTreeTemplate.main(NO_ARGS);
  }

  @Test
  public void testTail() {
    try {
      Tail.main(NO_ARGS);
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void testTailCoin() {
    TailCoin.main(NO_ARGS);
  }

  @Test
  public void testTask_assignment() {
    TaskAssignment.main(NO_ARGS);
  }

  @Test
  public void testTeamPhoto2() {
    TeamPhoto2.main(NO_ARGS);
  }

  @Test
  public void testTeam_photo_1() {
    TeamPhoto1.main(NO_ARGS);
  }

  @Test
  public void testTheoryOfEquality() {
    TheoryOfEquality.main(NO_ARGS);
  }

  @Test
  public void testThreeJugs() {
    ThreeJugs.main(NO_ARGS);
  }

  @Test
  public void testTiesElection() {
    TiesElection.main(NO_ARGS);
  }

  @Test
  public void testTournamentTree() {
    TournamentTree.main(NO_ARGS);
  }

  @Test
  public void testTowerHanoi() {
    TowerHanoi.main(NO_ARGS);
  }

  @Test
  public void testTransformStringToOther() {
    TransformStringToOther.main(NO_ARGS);
  }

  @Test
  public void testTransitiveClosure() {
    TransitiveClosure.main(NO_ARGS);
  }

  @Test
  public void testTreeDiameter() {
    TreeDiameter.main(NO_ARGS);
  }

  @Test
  public void testTwoExists() {
    String[] args = { "50" }; // number of vertices
    TwoExists.main(args);
  }

  @Test
  public void testTwoForAll() {
    String[] args = { "50" };
    TwoForAll.main(args);
  }

  @Test
  public void testUniformRandomNumberGeneration() {
    UniformRandomNumberGeneration.main(NO_ARGS);
  }

  @Test
  public void testUnion_intervals() {
    UnionIntervals.main(NO_ARGS);
  }

  @Test
  public void testUpdateBST() {
    // TODO(AA): throws NPE, wrote to Alex about it
    UpdateBST.main(NO_ARGS);
  }

  // @Test public void testUtils() { Utils.main(noArgs); }
  @Test
  public void testViewFromAbove() {
    ViewFromAbove.main(NO_ARGS);
  }

  @Test
  public void testViewSunset() {
    ViewSunset.main(NO_ARGS);
  }

  @Test
  public void testWiringCircuitBoard() {
    WiringCircuitBoard.main(NO_ARGS);
  }

  @Test
  public void testWordBreaking() {
    WordBreaking.main(NO_ARGS);
  }

  @Test
  public void testZeroOneKnapsack() {
    ZeroOneKnapsack.main(NO_ARGS);
  }

  @Test
  public void testZeroSumSubset() {
    ZeroSumSubset.main(NO_ARGS);
  }

  @Test
  public void testZippingListTemplate() {
    ZippingListTemplate.main(NO_ARGS);
  }

  @Test
  public void testisBinaryTreeABST_BFS() {
    IsBinaryTreeAbstBfs.main(NO_ARGS);
  }

  @Test
  public void testthree_sum() {
    String[] args = { "100" };
    ThreeSum.main(args);
  }

  // EPI 1.4

  @Test
  public void AddTwoNumberList() {
    AddTwoNumberList.main(NO_ARGS);
  }

  @Test
  public void AverageTop3Scores() {
    AverageTop3Scores.main(NO_ARGS);
  }

  @Test
  public void BinaryTreePostorderTraversalIterative() {
    BinaryTreePostorderTraversalIterative.main(NO_ARGS);
  }

  @Test
  public void BinaryTreePostorderTraversalIterativeAlternative() {
    BinaryTreePostorderTraversalIterativeAlternative.main(NO_ARGS);
  }

  @Test
  public void BinaryTreePreorderTraversalIterative() {
    BinaryTreePreorderTraversalIterative.main(NO_ARGS);
  }

  @Test
  public void Bonus() {
    Bonus.main(NO_ARGS);
  }

  @Test
  public void BonusImproved() {
    BonusImproved.main(NO_ARGS);
  }

  @Test
  public void CloneGraph() {
    CloneGraph.main(NO_ARGS);
  }

  @Test
  public void Combinations() {
    Combinations.main(NO_ARGS);
  }

  @Test
  public void ContainerWithMostWater() {
    String[] args = { "100" };
    ContainerWithMostWater.main(args);
  }

  @Test
  public void CyclicRightShift() {
    CyclicRightShift.main(NO_ARGS);
  }

  @Test
  public void FirstMissingPositive() {
    String[] args = { "100" };
    FirstMissingPositive.main(args);
  }

  @Test
  public void GenerateParentheses() {
    GenerateParentheses.main(NO_ARGS);
  }

  @Test
  public void GeneratingABSqrt2Improved() {
    String[] args = { "100" };
    GeneratingABSqrt2Improved.main(args);
  }

  @Test
  public void GrayCode() {
    GrayCode.main(NO_ARGS);
  }

  @Test
  public void HighestAffinityPairs() {
    HighestAffinityPairs.main(NO_ARGS);
  }

  @Test
  public void InsertInterval() {
    InsertInterval.main(NO_ARGS);
  }

  @Test
  public void InsertionSortList() {
    InsertionSortList.main(NO_ARGS);
  }

  @Test
  public void InterleavingString() {
    InterleavingString.main(NO_ARGS);
  }

  @Test
  public void JumpGame() {
    JumpGame.main(NO_ARGS);
  }

  @Test
  public void KThLargestElement() {
    KThLargestElement.main(NO_ARGS);
  }

  @Test
  public void KThNodeBinaryTree() {
    KThNodeBinaryTree.main(NO_ARGS);
  }

  @Test
  public void LeftJustifiedText() {
    LeftJustifiedText.main(NO_ARGS);
  }

  @Test
  public void ListPivoting() {
    ListPivoting.main(NO_ARGS);
  }

  @Test
  public void LongestContainedRange() {
    String[] args = { "100" };
    LongestContainedRange.main(args);
  }

  @Test
  public void LongestSubarrayWithDistinctEntries() {
    LongestSubarrayWithDistinctEntries.main(NO_ARGS);
  }

  @Test
  public void LongestValidParentheses() {
    LongestValidParentheses.main(NO_ARGS);
  }

  @Test
  public void LookAndSay() {
    LookAndSay.main(NO_ARGS);
  }

  @Test
  public void MergeTwoSortedArraysInPlace() {
    MergeTwoSortedArraysInPlace.main(NO_ARGS);
  }

  @Test
  public void NQueens() {
    NQueens.main(NO_ARGS);
  }

  @Test
  public void NumberSteps() {
    NumberSteps.main(NO_ARGS);
  }

  @Test
  public void PalindromeNumber() {
    PalindromeNumber.main(NO_ARGS);
  }

  @Test
  public void PalindromePartitioning() {
    PalindromePartitioning.main(NO_ARGS);
  }

  @Test
  public void PalindromePartitioningMinCuts() {
    PalindromePartitioningMinCuts.main(NO_ARGS);
  }

  @Test
  public void PascalTriangle1() {
    PascalTriangle1.main(NO_ARGS);
  }

  @Test
  public void PathSumBinaryTree() {
    PathSumBinaryTree.main(NO_ARGS);
  }

  @Test
  public void PermutationsAlternative() {
    PermutationsAlternative.main(NO_ARGS);
  }

  @Test
  public void PlusOne() {
    PlusOne.main(NO_ARGS);
  }

  @Test
  public void PopulatingNextRightPointers() {
    PopulatingNextRightPointers.main(NO_ARGS);
  }

  @Test
  public void PowerXY() {
    PowerXY.main(NO_ARGS);
  }

  @Test
  public void ReconstructAlmostBst() {
    ReconstructAlmostBst.main(NO_ARGS);
  }

  @Test
  public void RemoveDuplicatesFromSortedArray() {
    RemoveDuplicatesFromSortedArray.main(NO_ARGS);
  }

  @Test
  public void RemoveDuplicatesSortedList() {
    RemoveDuplicatesSortedList.main(NO_ARGS);
  }

  @Test
  public void RemoveElement() {
    RemoveElement.main(NO_ARGS);
  }

  @Test
  public void ReverseInteger() {
    ReverseInteger.main(NO_ARGS);
  }

  @Test
  public void ReverseLinkListFromSToF() {
    ReverseLinkListFromSToF.main(NO_ARGS);
  }

  @Test
  public void ReverseListInKGroup() {
    ReverseListInKGroup.main(NO_ARGS);
  }

  @Test
  public void RomanToInteger() {
    RomanToInteger.main(NO_ARGS);
  }

  @Test
  public void RookAttack() {
    RookAttack.main(NO_ARGS);
  }

  @Test
  public void SnakeString() {
    SnakeString.main(NO_ARGS);
  }

  @Test
  public void SortList() {
    SortList.main(NO_ARGS);
  }

  @Test
  public void SquareRootInt() {
    SquareRootInt.main(NO_ARGS);
  }

  @Test
  public void SubstringWithConcatenationOfAllWords() {
    SubstringWithConcatenationOfAllWords.main(NO_ARGS);
  }

  @Test
  public void SumRootToLeafBinaryTree() {
    SumRootToLeafBinaryTree.main(NO_ARGS);
  }

  @Test
  public void SurroundedRegions() {
    SurroundedRegions.main(NO_ARGS);
  }

  @Test
  public void TrappingRainWater() {
    TrappingRainWater.main(NO_ARGS);
  }

  @Test
  public void Triangle() {
    Triangle.main(NO_ARGS);
  }

  @Test
  public void UnconstructableChange() {
    UnconstructableChange.main(NO_ARGS);
  }

  @Test
  public void UniqueBinaryTreesAll() {
    UniqueBinaryTreesAll.main(NO_ARGS);
  }

  @Test
  public void ValidIPAddress() {
    ValidIPAddress.main(NO_ARGS);
  }

  @Test
  public void ValidPalindrome() {
    ValidPalindrome.main(NO_ARGS);
  }

  @Test
  public void ValidParentheses() {
    ValidParentheses.main(NO_ARGS);
  }

}
