﻿namespace OffByOne.Ql.Checker
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    using MoreDotNet.Extensions.Collections;

    using OffByOne.Ql.Checker.Contracts;
    using OffByOne.Ql.Checker.Messages.Base;

    public class CheckerReport : ICheckerReport
    {
        private readonly ISet<CheckerMessage> checkerMessages;

        public CheckerReport()
            : this(new List<CheckerMessage>())
        {
        }

        public CheckerReport(IEnumerable<CheckerMessage> messages)
        {
            if (messages == null)
            {
                throw new ArgumentNullException(nameof(messages));
            }

            this.checkerMessages = new HashSet<CheckerMessage>(messages);
        }

        public IEnumerable<CheckerMessage> InformationMessages => this.checkerMessages
            .OfType<InfoMessage>();

        public IEnumerable<CheckerMessage> Warnings => this.checkerMessages
            .OfType<WarningMessage>();

        public IEnumerable<CheckerMessage> Errors => this.checkerMessages
            .OfType<ErrorMessage>();

        public IEnumerable<CheckerMessage> AllMessages => this.checkerMessages;

        public bool HasErrors() => this.Errors.Any();

        public void Add(CheckerMessage message)
        {
            if (message == null)
            {
                throw new ArgumentNullException(nameof(message));
            }

            this.checkerMessages.Add(message);
        }

        public void Add(IEnumerable<CheckerMessage> messages)
        {
            if (messages == null)
            {
                throw new ArgumentNullException(nameof(messages));
            }

            messages.ForEach(this.Add);
        }
    }
}
