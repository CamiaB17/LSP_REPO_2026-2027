How was Assignment #2 organized?

One class, one method. ETLPipeline.main did everything — read the file, trimmed and validated fields, calculated pay, rounded it, figured out PayLevel and EmploymentStatus, built the output string, wrote the file, and printed the summary. Comments marked the ETL stages, but the code itself was one long procedural block.

What design changes did I make for Assignment #3?

I split that one method into six classes, each handling one piece of the pipeline: reading the file, parsing/validating a row, calculating payroll, and writing the output. ETLPipeline now just calls these in order instead of doing the work itself.

What classes did I introduce, and why?

Employee — holds one employee's data and results. Replaces the loose local variables from Assignment #2.
EmployeeParser — parses and validates one raw line, throws InvalidRowException if it should be skipped.
InvalidRowException — a simple way to signal "skip this row" without checking for nulls everywhere.
EmployeeCsvReader — reads the input file and counts rows read/skipped.
PayrollCalculator — has all the pay math: overtime, IT bonus, rounding, PayLevel, EmploymentStatus.
EmployeeCsvWriter — writes the output file.

How did I divide responsibilities differently?

In Assignment #2, reading a file and calculating pay lived in the same method, so I couldn't touch one without risking the other. Now each class only knows about its own job.

Why do you believe your Assignment #3 design is an improvement?

If the payroll rules changed, I'd only touch PayrollCalculator. If the input format changed, I'd only touch EmployeeCsvReader. Nothing else would need to move. 

AI and Internet Resources

Used Claude (Anthropic) to think through the class breakdown and review the implementation to make sure each class had a clear purpose. Transcript: https://claude.ai/share/06d3e189-1847-435a-9196-3a8f3971c9da 

Other Internet Resources Used: https://docs.oracle.com/javase/tutorial/essential/exceptions/creating.html 