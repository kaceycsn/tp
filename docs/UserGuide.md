<!-- omit in toc -->

# User Guide

<!-- omit in toc -->

## Table of Contents

- [User Guide](#user-guide)
  - [Table of Contents](#table-of-contents)
  - [Introduction](#introduction)
  - [Getting Started](#getting-started)
  - [Features](#features)
    - [Adding an entry: `/add`](#adding-an-entry-add)
      - [Supported Categories](#supported-categories)
    - [Deleting an entry: `/delete`](#deleting-an-entry-delete)
    - [Edit an entry: `/edit`](#edit-an-entry-edit)
    - [View an entry: `/view`](#view-an-entry-view)
    - [Filter options](#filter-options)
    - [Show help menu: `/help`](#show-help-menu-help)
    - [Exit Program: `/bye`](#exit-program-bye)
  - [Command Summary](#command-summary)
  - [Frequently Asked Questions](#frequently-asked-questions)

## Introduction

PocketPal is an expense tracking command line application that allows users to record their expenses in different
categories. Subsequently, users can monitor their expenses within a specified timeframe, or price range. They may also
monitor expenses in certain categories. This makes it easier for users to plan their budgets and make better financial
decisions.

## Getting Started

1. Ensure that you have Java `11` and above
   installed [(Installation Guide)](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)
2. Download our latest release of `PocketPal.jar` [here](https://github.com/AY2223S2-CS2113-W15-2/tp/releases)
3. Run the application
   with `java -jar PocketPal.jar` [(Running JAR Guide)](https://se-education.org/guides/tutorials/jar.html#running-jar-files)
4. You should see the following welcome screen
   ```
   Welcome to
   _____           _        _   _____      _
   |  __ \         | |      | | |  __ \    | |
   | |__) |__   ___| | _____| |_| |__) |_ _| |
   |  ___/ _ \ / __| |/ / _ \ __|  ___/ _` | |
   | |  | (_) | (__|   <  __/ |_| |  | (_| | |
   |_|   \___/ \___|_|\_\___|\__|_|   \__,_|_|

   How may I help you?
   ________________________________________________
   Enter a command or /help to see the list of commands available.
   > 
   ```
5. To enter an entry, you may use [`/add`](#adding-an-entry-add-add),
   or enter [`/help`](#show-help-menu-help-help) to view the help menu.

<!-- @@author adenteo -->
> The table below provides a summary of all the currently supported features in PocketPal.
> More detailed explanations on the usage of the commands are provided as well.

## Features

This user guide adopts the following conventions for the command-line syntax:

+ Angle brackets (`<>`) indicate that the enclosed arguments are mandatory.
+ Square brackets (`[]`) indicate that the enclosed arguments are optional.
+ Ellipsis (`...`) indicate that the preceding argument can be repeated several times in one command.
+ Pipe or vertical line (`|`) indicates a choice within an argument. You can select either one of them, but cannot
  select more than one.

**IMPORTANT NOTES**

- All arguments starting with a dash (`-`) will be treated as options. This includes negative integers, which would trigger unknown options exception

- For all specified options, only the arguments that follow the first declaration will be parsed. **i.e. any
  subsequent re-declarations of the same option will be ignored.**<br>For example, for the following
  input:<br>`/view -c food -c clothing`<br>Only entries in the `food` category will be listed.

If you face any problems, do visit the [FAQ](#frequently-asked-questions) segment!

| Command                              |                     Function                      |
|--------------------------------------|:-------------------------------------------------:|
| [/add](#adding-an-entry-add)         |                   Adds an entry                   |
| [/delete](#deleting-an-entry-delete) |                 Deletes an entry                  |
| [/edit](#edit-an-entry-edit)         |                  Edits an entry                   |
| [/view](#view-an-entry-view)         | Displays details of an entry e.g. Price, Category |
| [/help](#show-help-menu-help)        |              Displays the help menu               |
| [/bye](#exit-program-bye)            |              Terminates the program               |

<div style="text-align: right;">
   <a href="#table-of-contents"> Back to Table of Contents </a>
</div>

### Adding an entry: `/add`

Adds an entry to your current account.

Format: `/add -d <description> -c <category> -p <price>`

Options:

- `-d <description>`<br>`-description <description>`<br>Description of the entry.
    - All characters except comma (,) are valid.
    - Multiple words separated by spaces are allowed. However, a word should not start with dash (`-`) or it will be
      treated as an option.


- `-c <category>`<br>`-category <category>`<br>Category of the entry.
    - Must be a **one-word** category currently [supported](#supported-categories) in PocketPal.
    - Non case-sensitive.


- `-p <price>`<br>`-price <price>`<br>Price of the expense.
    - Must be a positive numeric or decimal value with no more than 2 decimal points.
    - Minimum value: `0.01`
    - Maximum value: `999999999.99`


- The order of the options are interchangeable, but they are all **required**.

#### Supported Categories

These are the categories currently supported by PocketPal:

`Clothing, Entertainment, Food, Medical, Personal, Transportation, Utilities, Others, Income`

---
Note: An entry with category `Income` will be added to your total income which can be seen in `/view`.

---


Example of usage:

`/add -d Lunch at McDonalds -category Food -price 19.9`

`/add -d Apple Macbook Air -p 1300 -category Personal`

`/add -p 1300 -c Personal -d Apple Macbook Air`

<div style="text-align: right;">
   <a href="#table-of-contents"> Back to Table of Contents </a>
</div>

### Deleting an entry: `/delete`

Deletes specified entry(s) from your current account.

The entry IDs can be obtained from the [`/view`](#view-an-entry-view) command.

Format: `/delete <index> [additional_index...]`

- `index`, `additional_index`: Index of the entry to be deleted.
    - Index must be a positive integer. The maximum index allowed is the total number of existing entries.
    - Additional indexes must be separated by spaces.

Example of usage:

`/delete 5`

`/delete 4 6 10`

<div style="text-align: right;">
   <a href="#table-of-contents"> Back to Table of Contents </a>
</div>

### Edit an entry: `/edit`

Edits a specified entry in your current account with the given flag(s).

Format: `/edit <index> [options]`

- `index`: Index of the entry to be deleted.
    - Only digits characters are allowed.
    - The maximum index allowed is the number of existing entries.

Options:

- `-d | -description` `<description>`: New description of the entry.
    - All characters except comma (,) are valid.
    - Multiple words are allowed.


- `-c | -category` `<category>`: New category of the entry.
    - Must be a **one-word** category currently [supported](#supported-categories) in PocketPal.
    - Non case-sensitive.


- `-p | -price` `<price>`: New price of the expense.
    - Must be a non-negative numeric or decimal value with no more than 2 decimal points.

The order of the options are interchangeable.

At least **one** of the options **must** be specified.

Example of usage:

`/edit 5 -p 10.50`

`/edit 5 -description Grab to school -c Transportation`

<div style="text-align: right;">
   <a href="#table-of-contents"> Back to Table of Contents </a>
</div>

### View an entry: `/view`

Displays a list of your current entries.

Format: `/view [count] [filter_options]`

- `count`: Number of entries to be listed.
    - Only digits characters are allowed.
    - If not specified, or if count is greater than number of existing entries, all entries will be listed.

### Filter options

**Filter by category**

- `-c | -category`  `<category>`: Category of entries to be listed.
    - Must be a **one-word** category currently supported in PocketPal.
    - Non case-sensitive.
    - If not specified, entries of all categories will be listed.

**Filter by price**

- `-sp | -startprice` `<min_price>`: Minimum price of entries to be listed.
- `-ep | -endprice` `<max_price>` Maximum price of entries to be listed.

Note:

- If `max_price` and `min_price` are both specified, all entries between and **inclusive** of `min_price` and
  `max_price` will be listed.
- If only `min_price` is specified, all expenses greater than or equal to `min_price` will be listed.
- If only `max_price` is specified, all expenses smaller than or equal to `max_price` will be listed.
- `min_price` should not be greater than `max_price`.
- All prices should have no more than 2 decimal points.
- If both specified prices are the same, the entries with that exact price will be listed.

**Filter by date range**

- `-sd, -startdate` `<start_date>`: Starting date of entries to be listed.
- `-ed, -enddate` `<end_date>`: Ending date of entries to be listed.

Note:

- `start_date`, `end_date` must be in `dd/MM/yyyy` format.
    - `dd` - Day of the month, from 01 to 31
    - `MM` - Month of the year, from 01 to 12
    - `yyyy` - Supported year, from 0001 to 9999

- Both flags are **required** if user wishes to use this
  option.
- `start_date` should not be after `end_date`.
- If both specified dates are the same, the entries on that date will be listed.

Order of options are **interchangeable**.

Example of usage:

`/view 10`

`/view -c food`

`/view -sd 12/03/2023 -ed 11/04/2023 -c food`

`/view -c food -sp 2 -sd 12/03/2023 -ed 11/04/2023`

<div style="text-align: right;">
   <a href="#table-of-contents"> Back to Table of Contents </a>
</div>

### Show help menu: `/help`

Displays the help menu.

Format: `/help`

<div style="text-align: right;">
   <a href="#table-of-contents"> Back to Table of Contents </a>
</div>

### Exit Program: `/bye`

Terminates PocketPal.

Format: `/bye`

<div style="text-align: right;">
   <a href="#table-of-contents"> Back to Table of Contents </a>
</div>

## Command Summary

| Command | Format                                                                                                                                                                                                                     |
|--------:|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|    /add | /add -d &lt;description&gt; -c &lt;category&gt;<br/>-p &lt;price&gt;<br/>e.g. <code>/add -d Gucci Bag -c Others -p 2000</code>                                                                                             |
|   /view | /view [count] [-c &lt;category&gt;] <br/>[-sp &lt;startprice&gt;] [-ep &lt;endprice&gt;]<br/>[-sd &lt;start_date&gt; -ed &lt;end_date&gt;] <br/>e.g. <code>/view -c food</code><br/>e.g.<code>/view -sp 100 -ep 200</code> |
|   /edit | /edit &lt;index&gt; [-c &lt;category&gt;] [-p &lt;price&gt;]<br/>[-d &lt;description&gt;]<br/>e.g. <code>/edit 1 -d Gucci Wallet -p 3000</code>                                                                            |
| /delete | /delete &lt;index&gt; [additional_index...]<br/>e.g. <code>/delete 1</code><br/>e.g. <code>/delete 1 5 10</code>                                                                                                           |
|   /help | /help                                                                                                                                                                                                                      |
|    /bye | /bye                                                                                                                                                                                                                       |

<!-- @@author -->

<div style="text-align: right;">
   <a href="#table-of-contents"> Back to Table of Contents </a>
</div>

## Frequently Asked Questions

> __Q:__ I am facing trouble starting the application. Do you know what might be the issue?
>
> __A:__ Please ensure that you have Java `11` and above installed on your machine.
> You may find more instructions at the [Getting Started](#getting-started) section

> __Q:__ How do I know whether the data entered is saved?
>
> __A:__ Your data is saved automatically when you interact with the application.
> There is no need to manually perform the save operation.

> __Q:__ How do I transfer my application data to another computer?
>
> __A:__ Your application data stored in `data/storage.txt`. To use PocketPal on another device,
> simply copy the `data` folder to the same directory as `PocketPal.jar` and start the
> application as per normal. Your stored entries will be automatically loaded.

> __Q:__ My application crashed. How do I report the problem to the developers?
>
> __A:__ We are sorry for the unpleasant experience with PocketPal, and we would be more than happy
> to solve the issue. You may file an issue on our GitHub stating how you arrived at the
> problem, so that our developers can assist you with the issue. Please also attach the application
> logs, which can be found at `logs/pocketpal.txt`

> __Q:__ I am a developer. How can I find the source code and contribute to PocketPal?
>
> __A:__ PocketPal is an open-source application, and we welcome developers to share their ideas.

>        You may find the source code on [GitHub](https://github.com/AY2223S2-CS2113-W15-2/tp/).
>
> __Q:__ I accidentally touched the data in my `storage.txt` file - what do I do?
>
> __A:__ The data in the `storage.txt` file is saved in the format `DESCRIPTION,PRICE,CATEGORY,DATE`. Make sure the data
> in the file is in the correct format, or else it will be overwritten with a blank data file. The category must be
> capitalized, and the date must be in the format `D MMM YYYY; HH:MM`.

<div style="text-align: right;">
   <a href="#table-of-contents"> Back to Table of Contents </a>
</div>

