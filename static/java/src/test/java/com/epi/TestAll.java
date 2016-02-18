package com.epi;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class TestAll {

  static final String[] NO_ARGS = new String[0];
  static final String[] SMALL_ARGS = {"10", "10"};
  static final String[] ARGS = NO_ARGS;

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
  public void AddOperatorsinString() {
    // TODO(AA): find implementation
    // AddOperatorsinString.main(noArgs);
  }

  @Test
  public void AddingCredits() {
    AddingCredits.main(ARGS);
  }

  @Test
  public void Anagrams() {
    Anagrams.main(ARGS);
  }

  @Test
  public void AnonymousLetter() {
    AnonymousLetter.main(ARGS);
  }

  @Test
  public void ApproximateSort() {
    ApproximateSort.main(ARGS);
  }

  @Test
  public void Arbitrage() {
    Arbitrage.main(ARGS);
  }

  @Test
  public void BSTLowestCommonAncestor() {
    BSTLowestCommonAncestor.main(ARGS);
  }

  @Test
  public void BSTSortedOrder() {
    BSTSortedOrder.main(ARGS);
  }

  @Test
  public void BSTToSortedDoublyList() {
    BSTToSortedDoublyList.main(ARGS);
  }

  @Test
  public void BalancedBinaryTree() {
    BalancedBinaryTree.main(ARGS);
  }

  @Test
  public void BentleyBsearch() {
    BentleyBsearch.main(ARGS);
  }

  @Test
  public void BigNumberMultiplication() {
    BigNumberMultiplication.main(ARGS);
  }

  @Test
  public void BiggestProductNMinus1() {
    BiggestProductNMinus1.main(ARGS);
  }

  @Test
  public void BiggestProductNMinus1Math() {
    BiggestProductNMinus1Math.main(ARGS);
  }

  // @Test public void BinaryOperator() { BinaryOperator.main(noArgs); }
  @Test
  public void BinarySearchAiEqI() {
    BinarySearchAiEqI.main(ARGS);
  }

  @Test
  public void BinarySearchCircularArray() {
    BinarySearchCircularArray.main(NO_ARGS);
  }

  @Test
  public void BinarySearchCircularArrayWithDuplicates() {
    BinarySearchCircularArrayWithDuplicates.main(NO_ARGS);
  }

  @Test
  public void BinarySearchFirstK() {
    BinarySearchFirstK.main(NO_ARGS);
  }

  @Test
  public void BinarySearchLargerK() {
    BinarySearchLargerK.main(NO_ARGS);
  }

  // @Test public void BinarySearchTree() { BinarySearchTree.main(noArgs); }
  @Test
  public void BinarySearchUnknownLength() {
    String[] args = { "100" };
    BinarySearchUnknownLength.main(args);
  }

  @Test
  public void BinaryTreeLevelOrder() {
    BinaryTreeLevelOrder.main(NO_ARGS);
  }

  @Test
  public void BinaryTreeLock() {
    BinaryTreeLock.main(NO_ARGS);
  }

  // @Test public void BinaryTreePrototypeTemplate() {
  // BinaryTreePrototypeTemplate.main(noArgs); }
  // @Test public void BinaryTreeUtils() { BinaryTreeUtils.main(noArgs); }
  // @Test public void BinaryTreeWithParentPrototype() {
  // BinaryTreeWithParentPrototype.main(noArgs); }
  @Test
  public void BuildBSTFromSortedArray() {
    BuildBSTFromSortedArray.main(ARGS);
  }

  @Test
  public void CanStringBePalindrome() {
    CanStringBePalindrome.main(ARGS);
  }

  // @Test public void CanStringBePalindromeHash() {
  // CanStringBePalindromeHash.main(noArgs); }
  // @Test public void CanStringBePalindromeSorting() {
  // CanStringBePalindromeSorting.main(noArgs); }
  @Test
  public void CelebrityFinding() {
    CelebrityFinding.main(ARGS);
  }

  @Test
  public void CheckingCycle() {
    CheckingCycle.main(ARGS);
  }

  @Test
  public void CheckingCycleAlternative() {
    CheckingCycleAlternative.main(ARGS);
  }

  @Test
  public void CircularQueue() {
    CircularQueue.main(ARGS);
  }

  @Test
  public void CloseSearch() {
    CloseSearch.main(ARGS);
  }

  @Test
  public void ClosestIntSameBits() {
    ClosestIntSameBits.main(ARGS);
  }

  @Test
  public void ClosestPairPoints() {
    String[] args = { "500" };
    ClosestPairPoints.main(args);
  }

  @Test
  public void ClosestPalindrome() {
    ClosestPalindrome.main(ARGS);
  }

  @Test
  public void ClosestStars() {
    ClosestStars.main(ARGS);
  }

  @Test
  public void ClosestToMedian() {
    String[] args = { "100", "10" };
    ClosestToMedian.main(args);
  }

  @Test
  public void CollatzConjecture() {
    CollatzConjecture.main(ARGS);
  }

  @Test
  public void CompareKthLargestInHeap() {
    String[] args = { "100" };
    CompareKthLargestInHeap.main(args);
  }

  @Test
  public void CompletionSearch() {
    CompletionSearch.main(ARGS);
  }

  @Test
  public void ComputingBinomialCoefficients() {
    ComputingBinomialCoefficients.main(ARGS);
  }

  @Test
  public void ComputingXPowN() {
    ComputingXPowN.main(ARGS);
  }

  @Test
  public void ConnectLeavesBinaryTree() {
    ConnectLeavesBinaryTree.main(ARGS);
  }

  @Test
  public void ConvertBase() {
    ConvertBase.main(ARGS);
  }

  @Test
  public void CopyingPostingsList() {
    // TODO(AA): wait for alex to come back on type definition of NodeT
    CopyingPostingsList.main(ARGS);
  }

  @Test
  public void CountInversions() {
    String[] args = { "100" };
    CountInversions.main(args);
  }

  @Test
  public void CountOccurrencesInSentence() {
    CountOccurrencesInSentence.main(ARGS);
  }

  @Test
  public void CountingSort() {
    CountingSort.main(ARGS);
  }

  @Test
  public void DeletionList() {
    DeletionList.main(ARGS);
  }

  @Test
  public void DescendantAndAncestor() {
    DescendantAndAncestor.main(ARGS);
  }

  @Test
  public void Division() {
    Division.main(ARGS);
  }

  @Test
  public void Doors() {
    Doors.main(ARGS);
  }

  // @Test public void DoublyLinkedListPrototypeTemplate() {
  // DoublyLinkedListPrototypeTemplate.main(noArgs); }
  @Test
  public void DrawingSkylines() {
    DrawingSkylines.main(ARGS);
  }

  @Test
  public void DutchNationalFlag() {
    DutchNationalFlag.main(ARGS);
  }

  @Test
  public void EliasCoding() {
    EliasCoding.main(ARGS);
  }

  @Test
  public void EliminateDuplicate() {
    EliminateDuplicate.main(ARGS);
  }

  @Test
  public void EquivClasses() {
    EquivClasses.main(ARGS);
  }

  @Test
  public void EvenOddMergeLinkedList() {
    EvenOddMergeLinkedList.main(ARGS);
  }

  @Test
  public void ExteriorBinaryTree() {
    ExteriorBinaryTree.main(ARGS);
  }

  @Test
  public void FindElementAppearsOnce() {
    FindElementAppearsOnce.main(ARGS);
  }

  @Test
  public void FindKLargestBST() {
    FindKLargestBST.main(ARGS);
  }

  @Test
  public void FindKthElementInTwoSortedArrays() {
    FindKthElementInTwoSortedArrays.main(ARGS);
  }

  @Test
  public void FindMissingAndDuplicate() {
    FindMissingAndDuplicate.main(ARGS);
  }

  @Test
  public void FindMissingAndDuplicateXOR() {
    FindMissingAndDuplicateXOR.main(ARGS);
  }

  @Test
  public void FindingMinMax() {
    FindingMinMax.main(ARGS);
  }

  @Test
  public void GCD() {
    GCD.main(ARGS);
  }

  @Test
  public void GassingUp() {
    GassingUp.main(ARGS);
  }

  @Test
  public void GaussianElimination() {
    GaussianElimination.main(ARGS);
  }

  @Test
  public void GaussianPrimes() {
    String[] args = { "35" };
    GaussianPrimes.main(args);
  }

  @Test
  public void GeneratingABSqrt2() {
    GeneratingABSqrt2.main(ARGS);
  }

  @Test
  public void HashDictionary() {
    HashDictionary.main(ARGS);
  }

  @Test
  public void HeightDetermination() {
    HeightDetermination.main(ARGS);
  }

  @Test
  public void HouseMajority() {
    HouseMajority.main(ARGS);
  }

  @Test
  public void HuffmanEncoding() {
    HuffmanEncoding.main(ARGS);
  }

  @Test
  public void ImageCompression() {
    ImageCompression.main(ARGS);
  }

  @Test
  public void IndirectSort() {
    try {
      IndirectSort.main(ARGS);
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void InorderTraversalNoStack() {
    InorderTraversalNoStack.main(ARGS);
  }

  @Test
  public void InorderTraversalWithParent() {
    InorderTraversalWithParent.main(ARGS);
  }

  @Test
  public void InterconvertingStringInteger() {
    InterconvertingStringInteger.main(ARGS);
  }

  @Test
  public void IntersectRectangle() {
    IntersectRectangle.main(ARGS);
  }

  @Test
  public void IntersectSortedArrays() {
    IntersectSortedArrays.main(ARGS);
  }

  @Test
  public void IsBinaryTreeABST() {
    IsBinaryTreeABST.main(ARGS);
  }

  @Test
  public void IsBinaryTreeABSTConstSpace() {
    IsBinaryTreeABSTConstSpace.main(ARGS);
  }

  @Test
  public void JobAssignment() {
    JobAssignment.main(ARGS);
  }

  @Test
  public void KBalancedBinaryTree() {
    KBalancedBinaryTree.main(ARGS);
  }

  @Test
  public void KLargestElementsBinaryHeap() {
    KLargestElementsBinaryHeap.main(ARGS);
  }

  @Test
  public void KthElementStreaming() {
    KthElementStreaming.main(ARGS);
  }

  @Test
  public void KthLargestElementLargeN() {
    String[] args = { "100", "10" };
    KthLargestElementLargeN.main(args);
  }

  @Test
  public void LRUCache() {
    LRUCache.main(ARGS);
  }

  // Could get one of two exceptions, so cannot use
  // the expected=... syntax
  //@Test(expected = ArrayIndexOutOfBoundsException.class)
  @Test
  public void LargeArray() {
    try {
      LargeArray.main(ARGS);
      fail("Expected ArrayIndexOutOfBoundsException");
    } catch (ArrayIndexOutOfBoundsException e) {
      // Expected, because of math overflow
    } catch (OutOfMemoryError e) {
      // This will be thrown if jvm is run with less than 6GB
    }
  }

  @Test public void LargestRectangleUnderSkyline() {
    LargestRectangleUnderSkyline.main(ARGS);
  }

  @Test
  public void LazyInit() {
    LazyInit.main(ARGS);
  }

  @Test
  public void LevenshteinDistance() {
    LevenshteinDistance.main(ARGS);
  }

  @Test
  public void LineMostPoints() {
    String[] args = { "50" };
    LineMostPoints.main(args);
  }

  // @Test public void LinkedListPrototypeTemplate() {
  // LinkedListPrototypeTemplate.main(noArgs); }
  @Test
  public void Linked_list() {
    LinkedList.main(ARGS);
  }

  @Test
  public void LoadBalancing() {
    LoadBalancing.main(ARGS);
  }

  @Test
  public void LongestIncreasingSubarray() {
    LongestIncreasingSubarray.main(ARGS);
  }

  // @Test public void LongestNondecreasingSubsequenceN2() {
  // LongestNondecreasingSubsequenceN2.main(noArgs); }
  // @Test public void LongestNondecreasingSubsequenceNlogn() {
  // LongestNondecreasingSubsequenceNlogn.main(noArgs); }
  @Test
  public void LongestSubarrayK() {
    String[] args = { "100" };
    LongestSubarrayK.main(args);
  }

  @Test
  public void LowestCommonAncestorHash() {
    LowestCommonAncestorHash.main(ARGS);
  }

  @Test
  public void LowestCommonAncestorNoParent() {
    LowestCommonAncestorNoParent.main(ARGS);
  }

  @Test
  public void LowestCommonAncestor() {
    LowestCommonAncestor.main(ARGS);
  }

  @Test
  public void MatrixRotation() {
    MatrixRotation.main(ARGS);
  }

  @Test
  public void MatrixRotationConstant() {
    MatrixRotationConstant.main(ARGS);
  }

  @Test
  public void MatrixRotationNaive() {
    MatrixRotationNaive.main(ARGS);
  }

  @Test
  public void MatrixSearch() {
    MatrixSearch.main(ARGS);
  }

  @Test
  public void MaxDifferenceKPairs() {
    MaxDifferenceKPairs.main(ARGS);
  }

  @Test
  public void MaxDifferenceUnlimitedPairs() {
    MaxDifferenceUnlimitedPairs.main(ARGS);
  }

  @Test
  public void MaxSubmatrixRectangle() {
    MaxSubmatrixRectangle.main(ARGS);
  }

  // @Test public void MaxSubmatrixRectangleBruteForce() {
  // MaxSubmatrixRectangleBruteForce.main(noArgs); }
  @Test
  public void MaxSubmatrixRectangleImproved() {
    String[] args = { "16", "32" };
    MaxSubmatrixRectangleImproved.main(args);
  }

  @Test
  public void MaxSubmatrixSquare() {
    MaxSubmatrixSquare.main(ARGS);
  }

  @Test
  public void MaxSumSubarray() {
    String[] args = { "100" };
    MaxSumSubarray.main(args);
  }

  @Test
  public void MaximumSubarrayInCircularArray() {
    String[] args = { "100" };
    // TODO(AA): ArrayIndexOutOfBoundsException: 1
    // com.epi.MaximumSubarrayInCircularArray.main(MaximumSubarrayInCircularArray.java:83)
    MaximumSubarrayInCircularArray.main(args);
  }

  @Test
  public void MaximumSubarrayInCircularArrayConstantSpace() {
    String[] args = { "100" };
    MaximumSubarrayInCircularArrayConstantSpace.main(args);
  }

  @Test
  public void MedianSortedCircularLinkedList() {
    MedianSortedCircularLinkedList.main(ARGS);
  }

  @Test public void MergeSortedArrays() {
    MergeSortedArrays.main(ARGS);
  }

  @Test
  public void MergeSortedLists() {
    MergeSortedLists.main(ARGS);
  }

  @Test
  public void MergeTwoBSTs() {
    MergeTwoBSTs.main(ARGS);
  }

  @Test
  public void MinimumDistance3SortedArrays() {
    String[] args = { "3" };
    MinimumDistance3SortedArrays.main(args);
  }

  @Test
  public void MinimumSubarrayDifference() {
    MinimumSubarrayDifference.main(ARGS);
  }

  @Test
  public void MinimumWaitingTime() {
    MinimumWaitingTime.main(ARGS);
  }

  @Test
  public void MissingElement() {
    MissingElement.main(ARGS);
  }

  @Test
  public void MultibetCardColorGame() {
    MultibetCardColorGame.main(ARGS);
  }

  @Test
  public void MultiplyShiftAdd() {
    MultiplyShiftAdd.main(ARGS);
  }

  @Test
  public void NearestRepetition() {
    String[] args = { "100" };
    NearestRepetition.main(args);
  }

  @Test
  public void NearestRestaurant() {
    NearestRestaurant.main(ARGS);
  }

  @Test
  public void NextPermutation() {
    NextPermutation.main(ARGS);
  }

  @Test
  public void NonconstructibleChange() {
    NonconstructibleChange.main(ARGS);
  }

  @Test
  public void NonUniformRandomNumberGeneration() {
    NonUniformRandomNumberGeneration.main(ARGS);
  }

  @Test
  public void NormalizedPathnames() {
    NormalizedPathnames.main(ARGS);
  }

  @Test
  public void NumberWays() {
    NumberWays.main(ARGS);
  }

  @Test
  public void NumberWaysObstacles() {
    NumberWaysObstacles.main(ARGS);
  }

  @Test
  public void OfflineMinimum() {
    OfflineMinimum.main(ARGS);
  }

  @Test
  public void OfflineSampling() {
    OfflineSampling.main(ARGS);
  }

  @Test
  public void OnlineMedian() {
    OnlineMedian.main(ARGS);
  }

  @Test
  public void OnlineSampling() {
    OnlineSampling.main(ARGS);
  }

  @Test
  public void OverlappingListsNoCycle() {
    OverlappingListsNoCycle.main(ARGS);
  }

  @Test
  public void OverlappingLists() {
    OverlappingLists.main(ARGS);
  }

  @Test
  public void PaintingIterative() {
    PaintingIterative.main(ARGS);
  }

  @Test
  public void PaintingRecursive() {
    PaintingRecursive.main(ARGS);
  }

  // @Test public void Pair() { Pair.main(noArgs); }
  @Test
  public void PalindromeLinkedList() {
    PalindromeLinkedList.main(ARGS);
  }

  @Test
  public void Parity() {
    Parity.main(ARGS);
  }

  // @Test public void Parity1() { Parity1.main(noArgs); }
  // @Test public void Parity2() { Parity2.main(noArgs); }
  // @Test public void Parity3() { Parity3.main(noArgs); }
  // @Test public void Parity4() { Parity4.main(noArgs); }
  @Test
  public void PermutationArray() {
    PermutationArray.main(ARGS);
  }

  // @Test public void PermutationArray1() { PermutationArray1.main(noArgs);
  // }
  // @Test public void PermutationArray2() { PermutationArray2.main(noArgs);
  // }
  @Test
  public void PhoneMnemonic() {
    PhoneMnemonic.main(ARGS);
  }

  @Test
  public void PickingUpCoins() {
    PickingUpCoins.main(ARGS);
  }

  @Test
  public void PickingUpCoinsDontLose() {
    PickingUpCoinsDontLose.main(ARGS);
  }

  @Test
  public void PlanningFishing() {
    PlanningFishing.main(ARGS);
  }

  @Test
  public void PointsCoveringIntervals() {
    PointsCoveringIntervals.main(ARGS);
  }

  @Test
  public void PointsCoveringIntervalsAlternative() {
    PointsCoveringIntervalsAlternative.main(ARGS);
  }

  // @Test public void PostingsListPrototype() {
  // PostingsListPrototype.main(noArgs); }
  @Test
  public void PowerSet() {
    PowerSet.main(ARGS);
  }

  @Test
  public void PowerSetAlternative() {
    PowerSetAlternative.main(ARGS);
  }

  @Test
  public void PrettyPrinting() {
    PrettyPrinting.main(ARGS);
  }

  @Test
  public void PrimeSieve() {
    String[] args = { "1000" };
    PrimeSieve.main(args);
  }

  @Test
  public void QueueFromStacks() {
    QueueFromStacks.main(ARGS);
  }

  @Test
  public void QueueUsingTwoIntegers() {
    QueueUsingTwoIntegers.main(ARGS);
  }

  @Test
  public void QueueWithMax() {
    QueueWithMax.main(ARGS);
  }

  @Test
  public void QueueWithMaxUsingDeque() {
    QueueWithMaxUsingDeque.main(ARGS);
  }

  @Test
  public void RPN() {
    RPN.main(ARGS);
  }

  @Test
  public void RabinKarp() {
    RabinKarp.main(ARGS);
  }

  @Test
  public void RangeLookupBST() {
    RangeLookupBST.main(ARGS);
  }

  @Test
  public void Rearrange() {
    Rearrange.main(ARGS);
  }

  @Test
  public void RebuildBSTPostorder() {
    RebuildBSTPostorder.main(ARGS);
  }

  @Test
  public void RebuildBSTPreorder() {
    RebuildBSTPreorder.main(ARGS);
  }

  @Test
  public void RebuildBSTPreorderBetter() {
    RebuildBSTPreorderBetter.main(ARGS);
  }

  @Test
  public void ReconstructBinaryTreePostInOrders() {
    ReconstructBinaryTreePostInOrders.main(ARGS);
  }

  @Test
  public void ReconstructBinaryTreePreInOrders() {
    ReconstructBinaryTreePreInOrders.main(ARGS);
  }

  @Test
  public void ReconstructPreorderWithNull() {
    ReconstructPreorderWithNull.main(ARGS);
  }

  @Test
  public void RegularExpression() {
    RegularExpression.main(ARGS);
  }

  @Test
  public void RemoveKthLastList() {
    RemoveKthLastList.main(ARGS);
  }

  @Test
  public void RenderingCalendar() {
    RenderingCalendar.main(ARGS);
  }

  @Test
  public void ReplaceAndRemove() {
    ReplaceAndRemove.main(ARGS);
  }

  @Test
  public void ReservoirSampling() {
    try {
      ReservoirSampling.main(ARGS);
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void ReverseBits() {
    ReverseBits.main(ARGS);
  }

  @Test
  public void ReverseLinkedListIterative() {
    ReverseLinkedListIterative.main(ARGS);
  }

  @Test
  public void ReverseLinkedListRecursive() {
    ReverseLinkedListRecursive.main(ARGS);
  }

  @Test
  public void ReverseWords() {
    ReverseWords.main(ARGS);
  }

  @Test
  public void RoadNetwork() {
    String[] args = { "50" };
    RoadNetwork.main(args);
  }

  @Test
  public void RobotBattery() {
    String[] args = { "100" };
    RobotBattery.main(args);
  }

  // @Test public void RotateArray() { RotateArray.main(noArgs); /}
  // @Test public void RotateArrayPermutation() {
  // RotateArrayPermutation.main(noArgs); }
  @Test
  public void RotateArrayTest() {
    RotateArrayTest.main(ARGS);
  }

  @Test
  public void RunLengthCompression() {
    RunLengthCompression.main(ARGS);
  }

  @Test
  public void ScoreCombination() {
    ScoreCombination.main(ARGS);
  }

  @Test
  public void ScorePermutation() {
    ScorePermutation.main(ARGS);
  }

  @Test
  public void SearchAPairSortedArray() {
    SearchAPairSortedArray.main(ARGS);
  }

  @Test
  public void SearchBSTFirstLargerK() {
    SearchBSTFirstLargerK.main(ARGS);
  }

  @Test
  public void SearchBSTForFirstOccurrenceIterative() {
    SearchBSTForFirstOccurrenceIterative.main(ARGS);
  }

  @Test
  public void SearchBSTForFirstOccurrenceRecursive() {
    SearchBSTForFirstOccurrenceRecursive.main(ARGS);
  }

  @Test
  public void SearchMajority() {
    SearchMajority.main(ARGS);
  }

  @Test
  public void SearchMaze() {
    SearchMaze.main(ARGS);
  }

  @Test
  public void SearchMinFirstBST() {
    SearchMinFirstBST.main(ARGS);
  }

  @Test
  public void SearchPostingsListIterative() {
    // TODO(AA): wit for alex to come back on NodeT
    SearchPostingsListIterative.main(ARGS);
  }

  @Test
  public void SearchPostingsListRecursive() {
    // TODO(AA): wit for alex to come back on NodeT
    SearchPostingsListRecursive.main(ARGS);
  }

  @Test
  public void SearchFrequentItems() {
    String[] args = { "100", "10" };
    SearchFrequentItems.main(args);
  }

  @Test
  public void ShortestPathFewestEdges() {
    ShortestPathFewestEdges.main(ARGS);
  }

  @Test
  public void ShortestUniquePrefix() {
    ShortestUniquePrefix.main(ARGS);
  }

  @Test
  public void SlidingWindow() {
    SlidingWindow.main(ARGS);
  }

  @Test
  public void SmallestSubarrayCoveringSet() {
    String[] args = { "100" };
    SmallestSubarrayCoveringSet.main(args);
  }

  // @Test public void SmallestSubarrayCoveringSetStream() {
  // SmallestSubarrayCoveringSetStream.main(noArgs); }
  @Test
  public void SortedListToBST() {
    SortedListToBST.main(ARGS);
  }

  @Test
  public void SpiralMatrix() {
    SpiralMatrix.main(ARGS);
  }

  @Test
  public void SpiralMatrixClockwise() {
    SpiralMatrixClockwise.main(ARGS);
  }

  @Test
  public void SpreadsheetEncoding() {
    SpreadsheetEncoding.main(ARGS);
  }

  @Test
  public void SquareRoot() {
    SquareRoot.main(ARGS);
  }

  @Test
  public void StableAssignment() {
    StableAssignment.main(ARGS);
  }

  @Test
  public void StackQueueUsingHeap() {
    StackQueueUsingHeap.main(ARGS);
  }

  @Test
  public void StackSorting() {
    String[] args = { "100" };
    StackSorting.main(args);
  }

  @Test
  public void StackWithMaxImproved() {
    StackWithMaxImproved.main(ARGS);
  }

  @Test
  public void StackWithMax() {
    StackWithMax.main(ARGS);
  }

  @Test
  public void StringInMatrix() {
    StringInMatrix.main(ARGS);
  }

  @Test
  public void SubseqCover() {
    SubseqCover.main(ARGS);
  }

  @Test
  public void Successor() {
    Successor.main(ARGS);
  }

  // @Test public void SudokuCheck() { SudokuCheck.main(noArgs); }
  @Test
  public void SudokuSolve() {
    SudokuSolve.main(ARGS);
  }

  @Test
  public void SwapBits() {
    SwapBits.main(ARGS);
  }

  @Test
  public void SymmetricBinaryTree() {
    SymmetricBinaryTree.main(ARGS);
  }

  @Test
  public void Tail() {
    try {
      Tail.main(ARGS);
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void TailCoin() {
    TailCoin.main(ARGS);
  }

  @Test
  public void TaskAssignment() {
    TaskAssignment.main(ARGS);
  }

  @Test
  public void TeamPhoto2() {
    TeamPhoto2.main(ARGS);
  }

  @Test
  public void TeamPhoto1() {
    TeamPhoto1.main(ARGS);
  }

  @Test
  public void TheoryOfEquality() {
    TheoryOfEquality.main(ARGS);
  }

  @Test
  public void ThreeJugs() {
    ThreeJugs.main(ARGS);
  }

  @Test
  public void TiesElection() {
    TiesElection.main(ARGS);
  }

  @Test
  public void TournamentTree() {
    TournamentTree.main(ARGS);
  }

  @Test
  public void TowerHanoi() {
    TowerHanoi.main(ARGS);
  }

  @Test
  public void TransformStringToOther() {
    TransformStringToOther.main(ARGS);
  }

  @Test
  public void TransitiveClosure() {
    TransitiveClosure.main(ARGS);
  }

  @Test
  public void TreeDiameter() {
    TreeDiameter.main(ARGS);
  }

  @Test
  public void TwoExists() {
    String[] args = { "50" }; // number of vertices
    TwoExists.main(args);
  }

  @Test
  public void TwoForAll() {
    String[] args = { "50" };
    TwoForAll.main(args);
  }

  @Test
  public void UniformRandomNumberGeneration() {
    UniformRandomNumberGeneration.main(ARGS);
  }

  @Test
  public void UnionIntervals() {
    UnionIntervals.main(ARGS);
  }

  @Test
  public void UpdateBST() {
    // TODO(AA): throws NPE, wrote to Alex about it
    UpdateBST.main(ARGS);
  }

  // @Test public void Utils() { Utils.main(noArgs); }
  @Test
  public void ViewFromAbove() {
    ViewFromAbove.main(ARGS);
  }

  @Test
  public void ViewSunset() {
    ViewSunset.main(ARGS);
  }

  @Test
  public void WiringCircuitBoard() {
    WiringCircuitBoard.main(ARGS);
  }

  @Test
  public void WordBreaking() {
    WordBreaking.main(ARGS);
  }

  @Test
  public void ZeroOneKnapsack() {
    ZeroOneKnapsack.main(ARGS);
  }

  @Test
  public void ZeroSumSubset() {
    ZeroSumSubset.main(ARGS);
  }

  @Test
  public void ZippingList() {
    ZippingList.main(ARGS);
  }

  @Test
  public void IsBinaryTreeABSTBFS() {
    IsBinaryTreeABSTBFS.main(ARGS);
  }

  @Test
  public void ThreeSum() {
    String[] args = { "100" };
    ThreeSum.main(args);
  }

  // EPI 1.4

  @Test
  public void AddTwoNumberList() {
    AddTwoNumberList.main(ARGS);
  }

  @Test
  public void AverageTop3Scores() {
    AverageTop3Scores.main(ARGS);
  }

  @Test
  public void BinaryTreePostorderTraversalIterative() {
    BinaryTreePostorderTraversalIterative.main(ARGS);
  }

  @Test
  public void BinaryTreePostorderTraversalIterativeAlternative() {
    BinaryTreePostorderTraversalIterativeAlternative.main(ARGS);
  }

  @Test
  public void BinaryTreePreorderTraversalIterative() {
    BinaryTreePreorderTraversalIterative.main(ARGS);
  }

  @Test
  public void Bonus() {
    Bonus.main(ARGS);
  }

  @Test
  public void BonusImproved() {
    BonusImproved.main(ARGS);
  }

  @Test
  public void CloneGraph() {
    CloneGraph.main(ARGS);
  }

  @Test
  public void Combinations() {
    Combinations.main(ARGS);
  }

  @Test
  public void ContainerWithMostWater() {
    String[] args = { "100" };
    ContainerWithMostWater.main(args);
  }

  @Test
  public void CyclicRightShift() {
    CyclicRightShift.main(ARGS);
  }

  @Test
  public void FirstMissingPositive() {
    String[] args = { "100" };
    FirstMissingPositive.main(args);
  }

  @Test
  public void GenerateParentheses() {
    GenerateParentheses.main(ARGS);
  }

  @Test
  public void GeneratingABSqrt2Improved() {
    String[] args = { "100" };
    GeneratingABSqrt2Improved.main(args);
  }

  @Test
  public void GrayCode() {
    GrayCode.main(ARGS);
  }

  @Test
  public void HighestAffinityPairs() {
    HighestAffinityPairs.main(ARGS);
  }

  @Test
  public void InsertInterval() {
    InsertInterval.main(ARGS);
  }

  @Test
  public void InsertionSortList() {
    InsertionSortList.main(ARGS);
  }

  @Test
  public void InterleavingString() {
    InterleavingString.main(ARGS);
  }

  @Test
  public void JumpGame() {
    JumpGame.main(ARGS);
  }

  @Test
  public void KThLargestElement() {
    KThLargestElement.main(ARGS);
  }

  @Test
  public void KThNodeBinaryTree() {
    KThNodeBinaryTree.main(ARGS);
  }

  @Test
  public void JustifyText() {
    JustifyText.main(ARGS);
  }

  @Test
  public void ListPivoting() {
    ListPivoting.main(ARGS);
  }

  @Test
  public void LongestContainedRange() {
    String[] args = { "100" };
    LongestContainedRange.main(args);
  }

  @Test
  public void LongestSubarrayWithDistinctEntries() {
    LongestSubarrayWithDistinctEntries.main(ARGS);
  }

  @Test
  public void LongestValidParentheses() {
    LongestValidParentheses.main(ARGS);
  }

  @Test
  public void LookAndSay() {
    LookAndSay.main(ARGS);
  }

  @Test
  public void MergeTwoSortedArraysInPlace() {
    MergeTwoSortedArraysInPlace.main(ARGS);
  }

  @Test
  public void NQueens() {
    NQueens.main(ARGS);
  }

  @Test
  public void NumberSteps() {
    NumberSteps.main(ARGS);
  }

  @Test
  public void PalindromeNumber() {
    PalindromeNumber.main(ARGS);
  }

  @Test
  public void PalindromePartitioning() {
    PalindromePartitioning.main(ARGS);
  }

  @Test
  public void PalindromePartitioningMinCuts() {
    PalindromePartitioningMinCuts.main(ARGS);
  }

  @Test
  public void PascalTriangle1() {
    PascalTriangle1.main(ARGS);
  }

  @Test
  public void PathSumBinaryTree() {
    PathSumBinaryTree.main(ARGS);
  }

  @Test
  public void PermutationsAlternative() {
    PermutationsAlternative.main(ARGS);
  }

  @Test
  public void PlusOne() {
    PlusOne.main(ARGS);
  }

  @Test
  public void PopulatingNextRightPointers() {
    PopulatingNextRightPointers.main(ARGS);
  }

  @Test
  public void PowerXY() {
    PowerXY.main(ARGS);
  }

  @Test
  public void ReconstructAlmostBst() {
    ReconstructAlmostBst.main(ARGS);
  }

  @Test
  public void RemoveDuplicatesFromSortedArray() {
    RemoveDuplicatesFromSortedArray.main(ARGS);
  }

  @Test
  public void RemoveDuplicatesSortedList() {
    RemoveDuplicatesSortedList.main(ARGS);
  }

  @Test
  public void RemoveElement() {
    RemoveElement.main(ARGS);
  }

  @Test
  public void ReverseInteger() {
    ReverseInteger.main(ARGS);
  }

  @Test
  public void ReverseLinkListFromSToF() {
    ReverseLinkListFromSToF.main(ARGS);
  }

  @Test
  public void ReverseListInKGroup() {
    ReverseListInKGroup.main(ARGS);
  }

  @Test
  public void RomanToInteger() {
    RomanToInteger.main(ARGS);
  }

  @Test
  public void RookAttack() {
    RookAttack.main(ARGS);
  }

  @Test
  public void SnakeString() {
    SnakeString.main(ARGS);
  }

  @Test
  public void SortList() {
    SortList.main(ARGS);
  }

  @Test
  public void SquareRootInt() {
    SquareRootInt.main(ARGS);
  }

  @Test
  public void SubstringWithConcatenationOfAllWords() {
    SubstringWithConcatenationOfAllWords.main(ARGS);
  }

  @Test
  public void SumRootToLeafBinaryTree() {
    SumRootToLeafBinaryTree.main(ARGS);
  }

  @Test
  public void SurroundedRegions() {
    SurroundedRegions.main(ARGS);
  }

  @Test
  public void TrappingRainWater() {
    TrappingRainWater.main(ARGS);
  }

  @Test
  public void Triangle() {
    Triangle.main(ARGS);
  }

  @Test
  public void UniqueBinaryTreesAll() {
    UniqueBinaryTreesAll.main(ARGS);
  }

  @Test
  public void ValidIPAddress() {
    ValidIPAddress.main(ARGS);
  }

  @Test
  public void ValidPalindrome() {
    ValidPalindrome.main(ARGS);
  }

  @Test
  public void ValidParentheses() {
    ValidParentheses.main(ARGS);
  }

}
