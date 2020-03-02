# RecyclerView Binding

Using Groupie to manage recycerview adapters and items.

Groupie takes care of proper diffing and makes working with different item types very easy.  It also
has great support for different group/sections of data making complex lists & grids much easier to
implement.

The alternative here would have been to simply implement all of the RecyclerView.Adapter and
RecyclerView.ViewHolder logic from scratch and make sure the diffing was taken into account as well.
This works fine, but by using Groupie it helps enforce some more consistency in how adapters/items
are created across different screens and provides plenty of room to grow into the features of Groupie
if complex lists are desired.