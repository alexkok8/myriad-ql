﻿using Microsoft.VisualStudio.TestTools.UnitTesting;
using Questionnaires.QL.AST.Types;
using System;
using System.Reflection;

namespace Tests.QL.Value
{
    class ValueTester
    {
        public static void TestBinaryOperation(string operation, OperationTestInput[] testSet)
        {
            foreach (var testCase in testSet)
            {
                try
                {
                    TestBinaryOperation((dynamic)testCase, operation);
                    Assert.IsTrue(testCase.ExpectedResult != null, string.Format("Operation {0} on operand type {1} is erroneously supported", operation, testCase.LeftHandSideOperand.GetType()));
                }
                catch (NotSupportedException)
                {
                    Assert.IsTrue(testCase.ExpectedResult == null, string.Format("Operation {0} on operand type {1} is not supported while it should be", operation, testCase.LeftHandSideOperand.GetType()));
                }
            }
        }

        // Use reflection to test operators
        public static void TestBinaryOperation(BinaryOperationTestInput testInput, string op)
        {
            var lhsValue = ValueCreator.CreateValue(testInput.LeftHandSideOperand);
            var rhsValue = ValueCreator.CreateValue(testInput.RightHandSideOperand);
            var operationMethod = lhsValue.GetType().GetMethod(op, new Type[] { rhsValue.GetType() });
            try
            {
                var result = operationMethod.Invoke(lhsValue, new object[] { (dynamic)rhsValue });
                ValueTester.Test((dynamic)result, testInput.ExpectedResult);
            }
            catch (TargetInvocationException ex)
            {
                throw ex.InnerException;
            }
        }

        public static void TestBinaryOperation(OperationTestInput testInput, string op)
        {
            var lhsValue = ValueCreator.CreateValue(testInput.LeftHandSideOperand);
            var operationMethod = lhsValue.GetType().GetMethod(op);
            try
            {
                var result = operationMethod.Invoke(lhsValue, new object[] { });
                ValueTester.Test((dynamic)result, testInput.ExpectedResult);
            }
            catch (TargetInvocationException ex)
            {
                throw ex.InnerException;
            }
        }

        public static void Test(IType value, object expected)
        {
            // We will only get here if none of the methods below match
            // That means that the types of the value and the expected value 
            // don't match.
            Assert.Fail();
        }

        public static void Test(BooleanType value, object expected)
        {
            Assert.AreEqual(expected.GetType(), typeof(bool));
            Assert.AreEqual((bool)expected, value.GetValue());
        }

        public static void Test(IntegerType value, object expected)
        {
            Assert.AreEqual(expected.GetType(), typeof(int));
            Assert.AreEqual((int)expected, value.GetValue());
        }

        public static void Test(MoneyType value, object expected)
        {
            Assert.AreEqual(expected.GetType(), typeof(decimal));
            Assert.AreEqual((decimal)expected, value.GetValue());
        }

        public static void Test(StringType value, object expected)
        {
            Assert.AreEqual(expected.GetType(), typeof(string));
            Assert.AreEqual((string)expected, value.GetValue());
        }
    }
}
