<!DOCTYPE html>
<html>
<head>
<jsp:include page="includes.jsp" />
<title>Tree Tutorial</title>
</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container">
		<h1>Genetic Tree Construction</h1>
		<p>
			Genetic trees represent the ways in which species evolve. 
			When species reproduce their offspring may inherit random mutations which occured in the parent's gametes.
			These mutations may be beneficial and give the child an edge to survive and reproduce more successfully causing the mutation
			to be a part of the general population after many generations. We can analyze sections of DNA of different species and via alignments
			determine which species are the most similar and which are the least similar. It can be inferred that species whose DNA sequences are similar
			are more closely related due to a lack of mutations between the two species. Therefore, a tree which shows how similar a species is gives
			evidence of how the species evolved.
		</p>
		<p>
			The genetic tree constuction algorithm used in this web application uses neighbor-joining. This concept will be illustrated below.
			Before we construct a tree, we must have a multiple alignment of the species we are considering. Suppose we have the following alignment
			of species A, B, C, and D.
		</p>
		<div class="panel panel-primary">
			<div class="panel-heading">Multiple Alignment</div>
			<div class="panel-body">
				Species A: AC-GTA<br>
				Species B: ATCG-A<br>
				Species C: TTCGGA<br>
				Species D: GCT-AT<br>
			</div>
		</div>

		<p>
			We construct a matrix which shows the distance between the pairwise species. For example the distance between species A and B is 3
			because there are 3 locations in the aligned sequences which do not match.
		<p>
		<div class="panel panel-primary">
			<div class="panel-heading">Distance Matrix</div>
			<div class="panel-body">
				<table>
					<tr>
						<td></td>
						<td>A</td>
						<td>B</td>
						<td>C</td>
						<td>D</td>
					</tr>
					<tr>
						<td>A</td>
						<td>-</td>
						<td>3</td>
						<td>4</td>
						<td>5</td>
					</tr>
					<tr>
						<td>B</td>
						<td>3</td>
						<td>-</td>
						<td>2</td>
						<td>6</td>
					</tr>
					<tr>
						<td>C</td>
						<td>4</td>
						<td>2</td>
						<td>-</td>
						<td>6</td>
					</tr>
					<tr>
						<td>D</td>
						<td>5</td>
						<td>6</td>
						<td>6</td>
						<td>-</td>
					</tr>
				</table>
			</div>
		</div>
		<p>Denote D_ij as the distance between species i and j in the matrix and u_i = (sum over all j (D_ij))/(n - 2)
		where n is the number of distinct species.
		From the distance matrix, we then select the two species i and j for which D_ij - u_i - u_j is smallest. When this number is small it
		indicates that the species are not only similar to each other but also that the species are less similar to the other species.
		Therefore we declare that that these two species are the most closely related species out of all of the species and we make a new node in the tree which
		is the parent of these two species. This new node represents a common ancestor of the two species. We then take out the rows and columns in the
		distance matrix for species i and j and add a new row and column for the common ancestor. Specifically, we add entries D_pk = (D_ik + D_jk - D_ij)/2
		where p represents the parent node (the simple derivation of this is given below). We continue this process until just two nodes remain and we
		lastly join these two nodes to get the final tree.
		</p>
		<div class="panel panel-primary">
			<div class="panel-heading">D_pk Derivation</div>
			<div class="panel-body">
				<img src="/assets/tutorials/treeDerivation.png">
			</div>
		</div>
		<p>We show this process in our example:</p>
		<div class="panel panel-primary">
			<div class="panel-heading">Step 1</div>
			<div class="panel-body">
				u_A = (D_AB + D_AC + D_AD) / 2 = (3 + 4 + 5)/2 = 6<br/>
				u_B = (D_BA + D_BC + D_BD) / 2 = (3 + 2 + 6)/2 = 5.5<br/>
				u_C = (D_CA + D_CB + D_CD) / 2 = (4 + 2 + 6)/2 = 6<br/>
				u_D = (D_DA + D_DB + D_DC) / 2 = (5 + 6 + 6)/2 = 8.5<br/>
			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">Step 2</div>
			<div class="panel-body">
				D_AB - u_A - u_B = 3 - 6 - 5.5 = -8.5<br/>
				D_AC - u_A - u_C = 4 - 6 - 6 = -8<br/>
				D_AD - u_A - u_D = 5 - 6 - 8.5 = -9.5<br/>
				D_BC - u_B - u_C = 2 - 5.5 - 6 = -9.5<br/>
				D_BD - u_B - u_D = 6 - 5.5 - 8.5 = -8<br/>
				D_CD - u_C - u_D = 6 - 6 - 8.5 = -8.5<br/>
			</div>
		</div>
		<p>We see that D_AD - u_A - u_D is smallest so we choose species A and D to make a new node in the tree. We also update the distance matrix.</p>
		<div class="panel panel-primary">
			<div class="panel-heading">Step 3</div>
			<div class="panel-body">
				D_pB = (D_AB + D_DB - D_AD)/2 = (3 + 6 - 5)/2 = 2<br/>
				D_pC = (D_AC + D_DC - D_AD)/2 = (4 + 6 - 5)/2 = 2.5<br/>
				<table>
					<tr>
						<td></td>
						<td>B</td>
						<td>C</td>
						<td>p</td>
					</tr>
					<tr>
						<td>B</td>
						<td>-</td>
						<td>2</td>
						<td>2</td>
					</tr>
					<tr>
						<td>C</td>
						<td>2</td>
						<td>-</td>
						<td>2.5</td>
					</tr>
					<tr>
						<td>p</td>
						<td>2</td>
						<td>2.5</td>
						<td>-</td>
					</tr>
				</table>
			</div>
		</div>
		<p>We now do one final iteration:</p>
		<div class="panel panel-primary">
			<div class="panel-heading">Step 4</div>
			<div class="panel-body">
				u_B = (D_BC + D_Bp) / 1 = (2 + 2)/2 = 2<br/>
				u_C = (D_CB + D_Cp) / 1 = (2 + 2.5)/2 = 2.25<br/>
				u_p = (D_pB + D_pC) / 1 = (2 + 2.5)/2 = 2.25<br/>
			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">Step 5</div>
			<div class="panel-body">
				D_BC - u_B - u_C = 2 - 2 - 2.25 = -2.25<br/>
				D_Bp - u_B - u_p = 2 - 2 - 2.25 = -2.25<br/>
				D_Cp - u_C - u_p = 2.5 - 2.25 - 2.25 = -2<br/>
			</div>
		</div>
		<p>We now make a new parent node (p2) of nodes B and C since they are smallest. Additionally since only two nodes are now left (p and p2)
		we combine them into a final node. And we get the following tree (shown on the left):
		</p>
		<div class="panel panel-primary">
			<div class="panel-heading">Final Output</div>
			<div class="panel-body">
				<img src="/assets/tutorials/treeOutput.png">
			</div>
		</div>
		<p>However, the tree construction algorithm outputs an un-rooted tree. For this reason, when we choose the species we are constructing a tree
		for, we can include a species which we know is the least related out of all the species (For example if we are constructing a tree of penguins
		we could include a hawk). This least related species that we include is called the outgroup. If we assume that species D is the outgroup of our
		example, then we get the rooted tree shown on the right above.
	</div>
</body>
</html>
