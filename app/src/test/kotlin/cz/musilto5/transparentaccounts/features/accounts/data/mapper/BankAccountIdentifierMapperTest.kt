package cz.musilto5.transparentaccounts.features.accounts.data.mapper

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.core.spec.style.FunSpec

class BankAccountIdentifierMapperTest : FunSpec({

    val tested = BankAccountIdentifierMapper()

    context("map with null inputs") {
        test("returns null when accountNumber is null") {
            tested.map(accountNumber = null, bankCode = "0800").shouldBeNull()
        }
        test("returns null when bankCode is null") {
            tested.map(accountNumber = "000000-0109213309", bankCode = null).shouldBeNull()
        }
    }

    context("map with dash in account number") {
        test("parses prefix and account number and formats toString correctly") {
            val result = tested.map("000000-0109213309", "0800").shouldNotBeNull()
            result.accountNumber.value shouldBe "0109213309"
            result.bankCode.value shouldBe "0800"
            result.prefix shouldBe "000000"
            result.toString() shouldBe "000000-0109213309 / 0800"
        }
        test("returns null when part after dash is blank") {
            tested.map("000000-", "0800").shouldBeNull()
        }
    }

    context("map without dash") {
        test("uses whole string as account number, null prefix, and formats toString correctly") {
            val result = tested.map("0109213309", "0800").shouldNotBeNull()
            result.accountNumber.value shouldBe "0109213309"
            result.bankCode.value shouldBe "0800"
            result.prefix.shouldBeNull()
            result.toString() shouldBe "0109213309 / 0800"
        }
    }

    context("map with whitespace") {
        test("trims whitespace around prefix and account number") {
            val result = tested.map("  000000 - 0109213309  ", "0800").shouldNotBeNull()
            result.accountNumber.value shouldBe "0109213309"
            result.prefix shouldBe "000000"
        }
    }

    context("map with explicit prefix parameter") {
        test("uses explicit prefix over parsed prefix") {
            val result = tested.map("000000-0109213309", "0800", prefix = "19").shouldNotBeNull()
            result.accountNumber.value shouldBe "0109213309"
            result.bankCode.value shouldBe "0800"
            result.prefix shouldBe "19"
        }
    }
})
