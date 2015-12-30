not(a) = nand(a, a) = aa
and(a, b) = nand(nand(a, b), nand(a, b)) = (ab)(ab)
or(a, b) = nand(nand(a, a), nand(b, b)) = (aa)(bb)
nor(a, b) = nand(nand(nand(a, a), nand(b, b)), nand(a, a), nand(b, b)) = ((aa)(bb))((aa)(bb))
xor(a, b) = nand(nand(a, nand(a, b)), nand(b, nand(a, b))) = (a(ab))(b(ab))
xnor(a, b) = nand(nand(nand(a, nand(a, b)), nand(b, nand(a, b))), nand(nand(a, nand(a, b)), nand(b, nand(a, b)))) = ((a(ab))(b(ab)))(a(ab)(b(ab)))


not: (aa)
and: ((ab)(ab))
or: ((aa)(bb))
nor: (((aa)(bb))((aa)(bb)))
xor: ((a(ab))(b(ab)))
xnor: (((a(ab))(b(ab)))(a(ab)(b(ab))))