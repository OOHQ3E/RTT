import unittest
from main import EgyenletMegoldo

class Testing(unittest.TestCase):

    def setUp(self):
        print("setting up")
        self.tester = EgyenletMegoldo()

    def test_nincsMegoldas(self):
        print("Teszt 1: nincs megoldás")
        self.assertEqual(self.tester.masodfoku_egyenlet_megoldo(1, 2, 8), (None, None), "Nem szabad lennie megoldásnak!")

    def test_egyMegoldas(self):
        print("Teszt 2: egy megoldás")
        self.assertEqual(self.tester.masodfoku_egyenlet_megoldo(1, -14, 49), (7, 7), "Egy megoldásnak kell lennie!")

    def test_kettoMegoldas(self):
        print("Teszt 3: kettő megoldás")
        self.assertEqual(self.tester.masodfoku_egyenlet_megoldo(1, 6, 8), (-2, -4), "Kettő megoldásnak kell lennie!")

    def test_TypeError(self):
        print("Teszt 4: típushiba")
        with self.assertRaises(TypeError):
            result = self.tester.masodfoku_egyenlet_megoldo(True,False,False)

    def tearDown(self):
        print("tearing down")

if __name__ == '__main__':
    unittest.main()