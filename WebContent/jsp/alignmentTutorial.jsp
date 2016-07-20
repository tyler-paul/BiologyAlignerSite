<!DOCTYPE html>
<html>
<head>
<jsp:include page="includes.jsp" />
<title>Alignment Tutorial</title>
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container">
		<h1>Sequence Alignments</h1>
		<p>DNA alignments can be used to infer the similarity of different
			species and to construct genetic trees. There are several alignment
			variants which can be chosen from. However, all alignment variations
			need a way to score different alignments in order to find an adequate
			or optimal solution. One approach to scoring is to use a distance
			matrix. An entry in the matrix describes the contribution of the
			alignment of two nucleotide bases to the overall score of the
			alignment.</p>

		<div class="panel panel-primary">
			<div class="panel-heading">Distance Matrix</div>
			<div class="panel-body">
				<img src="/assets/tutorials/matrix.png" />
			</div>
		</div>

		<p>
			For example, suppose we want to find an alignment of the sequences ACGTA and ATCGA. 
			Then if we use the distance matrix given above then the alignment given below would have a score of 5 - 2 - 4 + 5 -1 + 5 = 8 
		<p>
		<div class="panel panel-primary">
			<div class="panel-heading">Example Sequences</div>
			<div class="panel-body">
				AC-GTA<br> ATCG-A<br>
			</div>
		</div>
		<p>The single alignment algorithm used in this web application
			is able to find an optimal solution for the alignment of two DNA
			sequences. However, this algorithm (a dynamic programming approach)
			does not scale well to align an arbitrary number of sequences. For
			this reason, the multiple alignment algorithm this web application
			uses is a heuristic algorithm and does not find an optimal solution,
			although it still yields adequate results in practice. The
			implementation uses a greedy approach to align multiple sequences.</p>
		<p>Lastly, when aligning sequences either a global or local
			alignment can be chosen. If a global alignment is chosen then the
			algorithm will attempt to align the entirety of the chosen sequences.
			A local alignment will attempt to find the section of the chosen
			sequences which align the best. Leading and trailing nucleotides may
			be removed in a local alignment if they do not contribute to the
			overall score.</p>
		<p>
			For example, consider the sequences shown below.
		</p>
		<div class="panel panel-primary">
			<div class="panel-heading">Example Sequences</div>
			<div class="panel-body">
				AAAACCATGCCC<br> TTTTCCTTGAAA<br>
			</div>
		</div>
		<p>
		The sequences do not align well in the leading and trailing
		section, so a global alignment may not be appropriate and would likely yield an alignment like the one show below.
		A local alignment on the other hand would capture the significant sections of the sequence with high similarity.
		</p>
		<div class="panel panel-primary">
			<div class="panel-heading">Global Alignment</div>
			<div class="panel-body">
				----AAAACCATG---CCC<br>  TTTT----CCTTGAAA---<br>
			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">Local Alignment</div>
			<div class="panel-body">
				CCATG<br> CCTTG<br>
			</div>
		</div>
	</div>
</body>
</html>
